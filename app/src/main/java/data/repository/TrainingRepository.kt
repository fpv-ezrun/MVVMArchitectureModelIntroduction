package data.repository

import androidx.lifecycle.LiveData
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import okhttp3.HttpUrl
import okhttp3.OkHttpClient

class TrainingRepository (private val local: TrainingDao, private val remote: TrainingServices){
//    val localTrainings = local.getAllTraining()

    suspend fun getTrainingFromWeb(client: OkHttpClient,URL: HttpUrl): String {
        return remote.FetchTraining(client,URL)
    }

    suspend fun getLocalTraining(): LiveData<List<Training>> {
        return local.getAllTraining()
    }

    suspend fun insertUpdate(training: Training): Long {
       return local.updateInsert(training)
    }

    suspend fun delete(training: Training): Int {
        return local.delete(training)
    }

}