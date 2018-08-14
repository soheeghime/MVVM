package com.android.riela.livedatatestr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class MainActivityViewModel : ViewModel() {

    private var fruitList: MutableLiveData<List<String>> = MutableLiveData()

    fun getFruitList(): LiveData<List<String>> {
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
        fruitsString.add("Melon")

        fruitList.postValue(fruitsString)
    }
}
