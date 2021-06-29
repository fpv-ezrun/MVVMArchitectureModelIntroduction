package data.remote.services

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

interface TrainingServices {
    @Throws(IOException::class)
   fun FetchTraining(client: OkHttpClient, base: HttpUrl): String {
        val request = Request.Builder()
            .url(base)
            .build()
        val response = client.newCall(request).execute()
        print("request method:"+request.method().toString()+"\n")
        return response.body()!!.string()

    }
    fun TestFetch(String: String): String {
        return String
    }
}