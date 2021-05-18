package com.mindorks.framework.mvvm.testUtils

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.concurrent.TimeUnit

abstract class ServiceTest<T : Any>(private val defaultClient: DefaultClient = DefaultClient.Unauthenticated)
{
    sealed class DefaultClient
    {
        object Unauthenticated : DefaultClient()
        data class Authenticated(val domain: String) : DefaultClient()
    }

    @get:Rule val serverRule = MockWebServerRule()


    protected lateinit var subject: T
    protected abstract fun buildSubject(baseUrl: String, client: OkHttpClient): T


    @Before
    open fun setup()
    {
        MockitoAnnotations.initMocks(this)
    }



    /**
     * @param response Can be either a raw string or a filename, depending if it ends with ".json"
     */
    protected fun enqueueSuccessWithResponse(response: String)
    {
        val responseStr = if(response.endsWith(".json"))
            readResourceAsString(response)
        else
            response
        serverRule.server.enqueue(MockResponse().setResponseCode(200).setBody(responseStr))
        lastRequest = null
    }

    protected fun enqueueEmptySuccess()
    {
        serverRule.server.enqueue(MockResponse().setResponseCode(200))
        lastRequest = null
    }

    protected fun enqueueError(statusCode: Int = 500)
    {
        serverRule.server.enqueue(MockResponse().setResponseCode(statusCode))
        lastRequest = null
    }

    private var lastRequest: RecordedRequest? = null
    private fun takeRequest(): RecordedRequest
            = lastRequest ?: serverRule.server.takeRequest(1L, TimeUnit.SECONDS)!!.also { lastRequest = it }

    protected fun assertGET(path: String)
            = assertRequest("GET", path, null)

    protected fun assertPOST(path: String, expectedBody: String? = null)
            = assertRequest("POST", path, expectedBody)

    protected fun assertDELETE(path: String)
            = assertRequest("DELETE", path, null)

    protected fun assertPATCH(path: String, expectedBody: String? = null)
            = assertRequest("PATCH", path, expectedBody)

    protected fun assertPUT(path: String, expectedBody: String? = null)
            = assertRequest("PUT", path, expectedBody)

    private fun assertRequest(method: String, path: String, expectedBody: String?)
    {
        takeRequest().let {
            assertEquals(method, it.method)
            assertEquals(path, it.path)

            expectedBody?.let { expected ->
                if(expected.endsWith(".json"))
                {
                    val expectedResponseStr = readResourceAsString(expected).removeWhiteSpacesAndNewLines()
                    assertEquals(expectedResponseStr, it.body.readUtf8().removeWhiteSpacesAndNewLines())
                }
                else // Body as a raw string
                {
                    assertEquals(expected, it.body.readUtf8())
                }
            }
        }
    }

    protected fun assertHeaders(vararg headers: Pair<String, String>)
    {
        takeRequest().let { request ->
            headers.forEach { assertEquals(it.second, request.getHeader(it.first)) }
        }
    }
}