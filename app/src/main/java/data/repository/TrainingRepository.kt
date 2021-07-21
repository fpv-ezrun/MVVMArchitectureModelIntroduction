package data.repository

import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

class TrainingRepository(private val local: TrainingDao, private val remote: TrainingServices){
//    val localTrainings = local.getAllTraining()

    suspend fun getTraining(forceUpdate: Boolean, URL: HttpUrl = "http://localhost:3000/trainings".toHttpUrl()): List<Training> {
        return if(forceUpdate){
            println("lien de recherche: "+ "http://localhost:3000/trainings".toHttpUrl() +"\n")
            remote.FetchTrainingtoList(URL)
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