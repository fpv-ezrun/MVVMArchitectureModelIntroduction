package com.mindorks.framework.mvvm.testUtils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher()
{
    val server = MockWebServer()
    val baseUrl : String
        get() = server.url("/").toString()

    override fun starting(description: Description?)
    {
        print("MockServerRule start\n")
        super.starting(description)
        server.start()
    }

    override fun finished(description: Description?)
    {
        print("MockServerRule stop\n")
        super.finished(description)
        server.shutdown()
    }
}