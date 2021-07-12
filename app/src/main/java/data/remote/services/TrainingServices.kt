package data.remote.services

import com.google.gson.JsonObject
import data.db.entities.Training
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
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
    fun FetchTrainingtoList(client: OkHttpClient, base: HttpUrl): List<Training>{
        val obj = JSONObject("{trainings : "+FetchTraining(client, base)+"}")
        val TrainingArray : JSONArray = obj.getJSONArray("trainings")
        var Maliste : List<Training> = mutableListOf()
        val NamesTrainings : ArrayList<String> = ArrayList()
        val IdTrainings : ArrayList<Int> = ArrayList()
        for (j in 0 until TrainingArray.length()){
            val TrainingDetail: JSONObject = TrainingArray.getJSONObject(j)
            NamesTrainings.add(TrainingDetail.getString("name"))
            println("+++++++"+TrainingDetail.toString()+"\n")
            IdTrainings.add(TrainingDetail.getInt("id"))
            var monTraining : Training = Training(IdTrainings[j],NamesTrainings[j])
            Maliste+=monTraining
            println(Maliste)
            //Maliste.toMutableList()[j].name=NamesTrainings[j]
            //Maliste.toMutableList()[j].training_id=IdTrainings[j]
        }
        println("les différents noms enregistré"+NamesTrainings.toString()+"et id"+IdTrainings.toString())
        //Maliste.toMutableList().add(monTraining)
        println(Maliste.toString())
        return Maliste
    }
    fun TestFetch(String: String): String {
        return String
    }
}