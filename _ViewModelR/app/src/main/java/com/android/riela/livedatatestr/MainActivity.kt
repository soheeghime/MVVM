package com.android.riela.livedatatestr

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var model: MainViewModel

    private val viewFactory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
                MainViewModel() as T
            } else {
                modelClass.getConstructor(Application::class.java).newInstance(this@MainActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this, viewFactory).get(MainViewModel::class.java)
        var li = model.getFruitList()
        li.observe(this, Observer{
            it?.let{
                var adap = RecyclerAdapter(this, it)
                rList.adapter = adap
                rList.layoutManager = LinearLayoutManager(baseContext)
            }
        })

        addButton.setOnClickListener{
            Log.e("VR", "------ onclick")
            model.addFruit()
        }

        model.create()
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    private fun bindViewModel() {
        var mSubscription = CompositeDisposable()
        mSubscription.add(model.getSubjectTest1().subscribe{
            Log.e("VR", "------ [bindViewModel]  SUBJECT subscribe    1: $it")
        })

        mSubscription.add(model.getLoadingIndicatorVisibility().subscribe { it ->
            Log.e("VR", "------ [bindViewModel]  LOADING subscribe    : $it")
            setLoadingIndicatorVisibility(it)
        })
    }

    private fun setLoadingIndicatorVisibility(isVisible:Boolean){
        Log.e("VR", "------ [setLoadingIndicatorVisibility]  isVisible    : $isVisible")
        if(isVisible){
            loading_progress.visibility = View.VISIBLE
        }else{
            loading_progress.visibility = View.GONE
        }
    }
}
