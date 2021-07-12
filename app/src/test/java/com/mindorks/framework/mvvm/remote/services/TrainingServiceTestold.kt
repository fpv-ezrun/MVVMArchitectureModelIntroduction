 package com.mindorks.framework.mvvm.remote.services

import com.mindorks.framework.mvvm.testUtils.readResourceAsString
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class TrainingServiceTestold {
    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun assertGetAndBodyRequest() {
        val server = MockWebServer()
        print(readResourceAsString("training.json")+"\n")
        val maString = readResourceAsString("training.json")
        server.enqueue(MockResponse().setBody(readResourceAsString("training.json")))
        server.start()
        val baseUrl = server.url("/trainings")
        val bodyRequest = sendAndAssertGetRequest(OkHttpClient(), baseUrl)
        Assert.assertEquals(maString, bodyRequest)
    }

    @Throws(IOException::class)
    private fun sendAndAssertGetRequest(client: OkHttpClient, base: HttpUrl): String {
        val request = Request.Builder()
            .url(base)
            .build()
        val response = client.newCall(request).execute()
        print(request.method+"\n")
        Assert.assertEquals("GET",request.method)
        //print(response.body!!.contentLength().toString()+"\n")
        return response.body!!.string()
    }
}