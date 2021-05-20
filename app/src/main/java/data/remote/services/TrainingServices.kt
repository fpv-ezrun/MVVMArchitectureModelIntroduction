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

interface TrainingServices {
    companion object :
        UtilsFunctions<TrainingServices, String, OkHttpClient, Class<TrainingServices>>(constructor = { apiUrl, client, clazz ->

            val builder = Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)

            builder.build().create(clazz)
        })
    @GET("trainings")
    suspend fun fetchTraining(): Response<List<TrainingResponse>>}
