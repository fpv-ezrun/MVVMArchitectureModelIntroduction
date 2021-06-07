package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel(){
    var number = 0
    var numberSaisie = 0

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


}

