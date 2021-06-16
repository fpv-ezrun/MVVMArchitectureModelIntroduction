package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import data.repository.TrainingRepository

class TrainingViewModelold : ViewModel(){

    val ListTraining: MutableLiveData<List<Training>> by lazy {

        MutableLiveData<List<Training>>()
    }

    val TmpTraining: MutableLiveData<Training> by lazy {

        MutableLiveData<Training>()
    }
    lateinit var local: TrainingDao
    lateinit var remote: TrainingServices

    var myrepository: TrainingRepository = TrainingRepository(local, remote);





}
