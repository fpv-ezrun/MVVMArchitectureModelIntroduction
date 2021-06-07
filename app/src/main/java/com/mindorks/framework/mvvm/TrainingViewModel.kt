package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.db.entities.Training

class TrainingViewModel : ViewModel(){

    val ListTraining: MutableLiveData<List<Training>> by lazy {

        MutableLiveData<List<Training>>()
    }




}
