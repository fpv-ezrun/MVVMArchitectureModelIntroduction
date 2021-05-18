package data.repository;

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRepository {
   fun main() {
       print("totototototototo\n")
       val url = "localhost:3000/trainings"
       val jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(
           Request.Method.GET, url, null,
           { response ->
               print("Response: " + response.toString())
           },
           { error ->
               // TODO: Handle error
           }
       )
   }
   }
