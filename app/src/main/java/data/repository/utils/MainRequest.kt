package data.repository.utils
/*
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRequest: AppCompatActivity() {

    private var weatherData: TextView? = null

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherData = findViewById(R.id.textView)

        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    }*/
    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TrainingService::class.java)
        val call = service.getTrainingData()
        call.enqueue(object : Callback<TrainingResponse> {
            override fun onResponse(call: Call<TrainingResponse>, response: Response<TrainingResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!


                }
            }

            override fun onFailure(call: Call<TrainingResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    companion object {

        var BaseUrl = "localhost:3000/trainings"

    }
}*/