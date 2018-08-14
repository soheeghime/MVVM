package com.android.riela.livedatatestr

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class MainActivityViewModel : ViewModel() {

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
}
