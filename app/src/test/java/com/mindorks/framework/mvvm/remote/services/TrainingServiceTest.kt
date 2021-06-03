package com.mindorks.framework.mvvm.remote.services

import com.mindorks.framework.mvvm.testUtils.readResourceAsString
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import data.remote.services.TrainingServices
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.After
import org.junit.Before


class TrainingServiceTest : TrainingServices {
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
    @After
    fun close(){
        MockWebServer().shutdown()//arret du serveur mock car fin du test
    }
}