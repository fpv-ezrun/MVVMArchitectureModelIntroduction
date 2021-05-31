package com.mindorks.framework.mvvm.remote.services

import com.mindorks.framework.mvvm.testUtils.readResourceAsString
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import data.remote.services.TrainingServices


class TrainingServiceTest : TrainingServices {
    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun assertGetAndBodyRequest() {
        val server = MockWebServer()
        //print(readResourceAsString("training.json") +"\n")
        val maString = readResourceAsString("training.json")
        server.enqueue(MockResponse().setBody(readResourceAsString("training.json")))
        server.start()
        val baseUrl = server.url("/trainings")
        Assert.assertEquals(maString,testFetchTraining(OkHttpClient(), baseUrl))
    }
}