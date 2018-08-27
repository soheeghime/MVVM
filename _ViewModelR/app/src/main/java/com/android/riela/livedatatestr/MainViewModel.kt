package com.android.riela.livedatatestr

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private var fruitList: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun getFruitList(): LiveData<ArrayList<String>> {
        if (fruitList.value == null){
            loadRandomLists()
            //loadFruits()
        }
        return fruitList
    }

    private fun loadFruits() {
        var fruitsString = ArrayList<String>()
        fruitsString.add("Mango")
        fruitsString.add("Apple")
        fruitsString.add("Orange")
        fruitsString.add("Banana")
        fruitsString.add("Grapes")
        fruitsString.add("Plums")

        fruitList.value = fruitsString
    }

    private fun loadRandomLists() {
        val random = Random()
        val items = ArrayList<String>()
        for (i in 1..10000) {
            var r = random.nextFloat() * 1000
            items.add(r.toString())
        }

        fruitList.value = items
    }

    fun addFruit(){
        var strList: ArrayList<String>? = fruitList.value
        strList?.add(0, "MELON!!!!")

        fruitList.value = strList
    }

    private var subject = Observable.create<String> { subscriber ->
        try {
            subscriber.onNext("안녕하세요")
            subscriber.onNext("반갑습니다")
            subscriber.onNext("종료하겠습니다")
            subscriber.onComplete()

            mLoadingSubject.onNext(true)
            subscriber.onNext("--- FALSE ")
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }.delay(3L, TimeUnit.SECONDS)

    private var subject2 = Observable.create<String> { subscriber ->
        try {
            subscriber.onNext("---1111----싱글입니")
            subscriber.onComplete()
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }

    fun getSubjectTest1(): Observable<String> {
        return subject
    }

    fun getLoadingIndicatorVisibility() : BehaviorSubject<Boolean>{
        return mLoadingSubject
    }

    private var mLoadingSubject = BehaviorSubject.create<Boolean>()

    fun create() {
        subject.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {text ->
                    Log.d("RX", "------- TRUE !!!!! : $text")
                    mLoadingSubject.onNext(false)
                }

        var strList: ArrayList<String>? = fruitList.value

        Observable.fromIterable(strList)
                .filter{it == "Plums"}
                .concatWith(subject2).subscribe {text ->
                    Log.d("RX", "------- CONCAT  onNext : $text")
                }
    }
}
