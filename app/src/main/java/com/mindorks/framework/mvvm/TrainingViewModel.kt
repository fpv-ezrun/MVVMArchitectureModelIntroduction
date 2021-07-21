package com.mindorks.framework.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import data.repository.TrainingRepository
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

//Pour instancier le repository, il doit faire appel aux fonctions lors des gets et sets, il doit aussi ne pas être instancicié dans le main
class TrainingViewModel : ViewModel() {
    lateinit var repository: TrainingRepository

    val ListTraining: MutableLiveData<List<Training>> by lazy {         //pour set une valeur d'un mutable livedata, il faut soit utiliser setValue, soit utilisé postValue ce qui permet
                                                                        //    au main thread de set la valeur voulue

        MutableLiveData<List<Training>>()
    }

    val TmpTrainingString: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
    var TmpString = ""
    lateinit var TmpTraining : Training
    var remote:TrainingServices = TrainingServices()



    //Initialisation de la base de données

    fun init(dao: TrainingDao){
        repository = TrainingRepository(dao,remote)    //initialisation du repository pour l'utiliser à travers d'autre fonction de get et de set
    }

    suspend fun deleteLocalTrainingFromRepository(TrainingDelete:Training){
        repository.delete(TrainingDelete)
    }


    //setter


    suspend fun setLocalTrainingFromRepository(InsertTraining: Training){
        repository.insertUpdate(InsertTraining)

    }








    //getter

    suspend fun getTrainings(boolean: Boolean,  URL: HttpUrl = "http://localhost:3000/trainings".toHttpUrl()!!): List<Training> {
       // return repository.getLocalTraining()
        return repository.getTraining(boolean,URL)
    }

    suspend fun getLocalTrainingsByIDFromRepository(IdTraining : Int): Training {
        return repository.getTrainingById(IdTraining)
    }





   /////////////////////////////////OLD//////////////////////////////////////
    var number = 0
    lateinit var testlateinit : List<Training>
    var numberSaisie = 0

    val currentMul2: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }
    val currentMul4: MutableLiveData<String> by lazy {

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

