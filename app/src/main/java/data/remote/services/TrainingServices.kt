package data.remote.services


import data.remote.responses.TrainingResponse
import retrofit2.Response
import retrofit2.http.*

interface TrainingServices {
    @GET("localhost:3000/trainings")
    suspend fun fetchTraining(): Response<List<TrainingResponse>>}
