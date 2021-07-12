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
        var monTraining : Training = Training(training_id=0, name="vttDelete")
        var Maliste : List<Training> = mutableListOf()
        val NamesTrainings : ArrayList<String> = ArrayList()
        val IdTrainings : ArrayList<Int> = ArrayList()
        var cpt: Int = 0
        for (j in 0 until TrainingArray.length()){
            val TrainingDetail: JSONObject = TrainingArray.getJSONObject(j)
            NamesTrainings.add(TrainingDetail.getString("name"))
            println("+++++++"+TrainingDetail.toString()+"\n")
            IdTrainings.add(TrainingDetail.getInt("id"))
            monTraining.training_id=IdTrainings[cpt]
            monTraining.name=NamesTrainings[cpt]
            Maliste+=monTraining
            println(Maliste)
            cpt++
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