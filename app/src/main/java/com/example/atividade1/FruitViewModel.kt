package com.example.atividade1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atividade1.data.FruitData

class FruitViewModel: ViewModel() {
    private val fruitsLiveData: MutableLiveData<MutableList<FruitData>> = createBaseList(5)

    fun addFruit(fruit: FruitData){
        fruitsLiveData.value?.add(fruit)
    }

    fun getFruits(): LiveData<MutableList<FruitData>>{
        return  fruitsLiveData
    }

    private fun createBaseList(size: Int): MutableLiveData<MutableList<FruitData>> {
        val fruits: MutableLiveData<MutableList<FruitData>>  = MutableLiveData()
        for (i in 1..size) fruits.value?.add(FruitData("Fruta ${i}", "Descrição da fruta ${i}", null))
        return  fruits
    }

}