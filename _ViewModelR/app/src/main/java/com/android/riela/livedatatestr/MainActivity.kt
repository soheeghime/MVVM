package com.android.riela.livedatatestr

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewFactory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java!!)) {
                MainActivityViewModel() as T
            } else {
                modelClass.getConstructor(Application::class.java).newInstance(this@MainActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var model = ViewModelProvider(this, viewFactory).get(MainActivityViewModel::class.java)
        var li = model.getFruitList()
        li.observe(this, Observer{
            it?.let{
                var adap = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, it)
                list.adapter = adap
            }
        })

        addButton.setOnClickListener{
            Log.e("VR", "------ onclick")
            model.addFruit()
        }
    }
}
