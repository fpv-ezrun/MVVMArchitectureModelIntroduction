package com.mindorks.framework.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import data.repository.TrainingRepository
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.http.Url
import java.net.URL

//Pour instancier le repository, il doit faire appel aux fonctions lors des gets et sets, il doit aussi ne pas être instancicié dans le main
class TestViewModel : ViewModel() {
    val ListTraining: MutableLiveData<List<Training>> by lazy {

        MutableLiveData<List<Training>>()
    }

    val TmpTrainingString: MutableLiveData<String> by lazy {

        MutableLiveData<String>()
    }

    var TmpString = ""
    lateinit var TmpTraining : Training
    /*lateinit var local : TrainingDao
    lateinit var remote : TrainingServices
    var repository : TrainingRepository = TrainingRepository(local,remote)*/

    //setter

     fun  setTmpTrainingString(value:String){
        TmpString = value
        TmpTrainingString.value = value
    }

     fun setTrainingListTraining(value:Training,index:Int){
        TmpTraining = value
        ListTraining.value?.get(index)?.training_id = value.training_id
        ListTraining.value?.get(index)?.name = value.name
    }


 /*   suspend fun setTrainingLocal(Training:Training){
        repository.insertUpdate(Training)
    }

    suspend fun deleteLocalTraining(Training: Training){
        repository.delete(Training)
    }
*/






    //getter

    fun getTmpTrainingString(): String {
        return TmpTrainingString.value.toString()
    }

    fun getTrainingListTraining(index:Int): Training{
       // TmpTraining = ListTraining.value?.get(index)!!
        return TmpTraining
    }
    /*suspend fun getListTrainingLocal(): LiveData<List<Training>> {
            return repository.getLocalTraining()
            //return repository.getTrainingFromWeb(OkHttpClient(),Urlremote)
    }
    suspend fun getListTrainingFromWeb(urlApi: HttpUrl): String { //TODO Parser le json(String) pour créer un livedata<list<training>>
        return repository.getTrainingFromWeb(OkHttpClient(), urlApi)
    }*/


    //lateinit var local: TrainingDao
    //lateinit var remote: TrainingServices

  //  var myrepository: TrainingRepository = TrainingRepository(local, remote);


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

