package data.repository

import androidx.lifecycle.LiveData
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import okhttp3.HttpUrl
import okhttp3.OkHttpClient

class TrainingRepository (private val local: TrainingDao, private val remote: TrainingServices){
//    val localTrainings = local.getAllTraining()

    suspend fun getTraining(forceUpdate: Boolean, URL: HttpUrl = HttpUrl.get("http://localhost:3000/trainings")!!): List<Training> {
        return if(forceUpdate){
            remote.FetchTrainingtoList(OkHttpClient(),URL)
        }else{
            local.getAllTraining()
        }
    }

    suspend fun insertUpdate(training: Training): Long {
       return local.updateInserttest(training)
    }

    suspend fun delete(training: Training): Int {
        return local.delete(training)
    }

    suspend fun getTrainingById(trainingId:Int): Training {
        return local.gettrainingid(trainingId)
    }

   /* suspend fun getTraining(client : OkHttpClient,Url: HttpUrl, boolean: Boolean): List<Training> {
        if (boolean) {
            getTrainingFromWeb(client, Url)
        } else {
            return getLocalTraining()
        }

    }*/

}