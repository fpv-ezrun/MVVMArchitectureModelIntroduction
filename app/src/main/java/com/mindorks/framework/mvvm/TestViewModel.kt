package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.db.entities.Training

class TestViewModel : ViewModel(){
    var number = 0
    var numberSaisie = 0

    val currentNumber: MutableLiveData<Int> by lazy{

        MutableLiveData<Int>()
    }
    val currentNumberSaisie: MutableLiveData<Int> by lazy{

        MutableLiveData<Int>()
    }

    val currentBoolean: MutableLiveData<Boolean> by lazy{

        MutableLiveData<Boolean>()
    }

    val currentMul2: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
    val currentMul3: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
    val currentMul5: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
    val currentMul7: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
 
    val currentTraining: MutableLiveData<Training> by lazy { // TODO

        MutableLiveData<Training>()
    }


}

