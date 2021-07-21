package com.mindorks.framework.mvvm.remote.services

import com.mindorks.framework.mvvm.testUtils.readResourceAsString
import data.db.entities.Training
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import data.remote.services.TrainingServicesinter
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.After
import org.junit.Before


class TrainingServiceTest : TrainingServicesinter {
   var baseUrl : HttpUrl = "https://www.youtube.com/user/WatchTheDaily/videos".toHttpUrlOrNull()!! //

    @Before
   fun setup() {
       val server = MockWebServer()
       server.enqueue(MockResponse().setBody(readResourceAsString("training.json")))
       server.start()
       baseUrl = server.url("/trainings")
   }
    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun assertGetAndBodyRequest() {
        val maString = readResourceAsString("training.json")
        Assert.assertEquals(maString,FetchTraining(OkHttpClient(), baseUrl))
    }

    @Test
    fun testtoList(){
        val listTraining: List<Training>
        val baseUrltest: HttpUrl = "http://localhost:3000/trainings".toHttpUrl()
        val testTraining: Training = Training(3, "training 3")
        listTraining=FetchTrainingtoList(baseUrltest)
        assert(listTraining.contains(testTraining))
        println("resultat "+listTraining.toString()+"resultat "+"\n")
    }

   /* @Test
    fun assertReadJson(){
        var ListTraining : ArrayList<Training>
        var listType : Type
        listType = TypeToken<ArrayList<Training>>() {}.type
        ListTraining = Gson().fromJson(readResourceAsString("training.json"), listType)
     val Test = GsonBuilder()
         .serializeNulls()
         .disableHtmlEscaping()
         .create()
        println("gson formaté :"+Test.toJson(readResourceAsString("training.json")))
    }*/
    @After
    fun close(){
        MockWebServer().shutdown()//arret du serveur mock car fin du test
    }
}