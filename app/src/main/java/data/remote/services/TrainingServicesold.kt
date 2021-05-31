package data.remote.services


import com.google.gson.GsonBuilder
import data.remote.responses.TrainingResponse
import retrofit2.Response
import retrofit2.http.*
import data.utils.UtilsFunctions
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

interface TrainingServicesold {
    companion object :
        UtilsFunctions<TrainingServicesold, String, OkHttpClient, Class<TrainingServicesold>>(constructor = { apiUrl, client, clazz ->

            val builder = Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())

            builder.build().create(clazz)
        })
    @GET("trainings")
    suspend fun fetchTraining(): Response<List<TrainingResponse>>}
