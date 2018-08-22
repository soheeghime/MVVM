package com.android.riela.livedatatestr

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import java.util.ArrayList

class MainViewModel : ViewModel() {

    private var fruitList: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun getFruitList(): LiveData<ArrayList<String>> {
        if (fruitList.value == null){
            loadFruits()
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

    fun addFruit(){
        var strList: ArrayList<String>? = fruitList.value
        strList?.add("MELON!!!!")

        fruitList.value = strList
    }

    var subject = Observable.create<String> { subscriber ->
        try {
            subscriber.onNext("안녕하세요")
            subscriber.onNext("반갑습니다")
            subscriber.onNext("종료하겠습니다")
            subscriber.onComplete()
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }

    var subject2 = Observable.create<String> { subscriber ->
        try {
            subscriber.onNext("---1111----싱글입니")
            subscriber.onComplete()
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }

    public fun create() {
        subject.subscribe({text ->
            Log.d("RX", "------- onNext : $text")
        })

        var strList: ArrayList<String>? = fruitList.value

        Observable.fromIterable(strList).filter{it == "Plums"}

                .concatWith(subject2).subscribe({text ->
                    Log.d("RX", "------- CONCAT  onNext : $text")

                })
    }
}
