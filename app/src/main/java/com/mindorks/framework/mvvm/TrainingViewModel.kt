package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.db.entities.Training
import data.repository.TrainingRepository

class TrainingViewModel : ViewModel(){

    val ListTraining: MutableLiveData<List<Training>> by lazy {

        MutableLiveData<List<Training>>()
    }

    lateinit var repository : TrainingRepository





}
