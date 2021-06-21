package com.mindorks.framework.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mindorks.framework.mvvm.testUtils.mockReturn
import com.mindorks.framework.mvvm.testUtils.readResourceAsString
import com.nhaarman.mockitokotlin2.verify
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import data.repository.TrainingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TrainingRepositoryTest {

    var baseUrl : HttpUrl = "https://www.youtube.com/user/WatchTheDaily/videos".toHttpUrlOrNull()!!
    lateinit var ListTraining : List<Training>
    var monString = readResourceAsString("training.json")
    var monTraining : Training = Training(training_id=1, name="vttDelete")
    var monClient = OkHttpClient()
    private lateinit var subject: TrainingRepository

    @Mock
    lateinit var remote: TrainingServices
    @Mock
    lateinit var local: TrainingDao

    @Before
    fun setup(){
      MockitoAnnotations.initMocks(this)
        val server = MockWebServer()
       // server.enqueue(MockResponse().setBody(readResourceAsString("training.json")))
       // server.start()
        baseUrl = server.url("/trainings")
        subject = TrainingRepository(local, remote)
      TrainingRepository(local, remote)
        runBlocking {
           ListTraining = listOf(Training(training_id=1, name="Foot"))
            mockReturn(local.getAllTraining(),ListTraining)
            mockReturn(remote.FetchTraining(monClient,baseUrl), monString)
            mockReturn(local.updateInsert(monTraining), 1)
            mockReturn(local.delete(monTraining), 1)

        }

    }
    @Test
     fun localGetAllTraining() = runBlockingTest {
        Assert.assertEquals(ListTraining,subject.getLocalTraining())
        verify(local).getAllTraining()
    }
    @Test
    fun remoteGetAllTraining() = runBlockingTest{
        Assert.assertEquals(monString,subject.getTrainingFromWeb(monClient,baseUrl))
        verify(remote).FetchTraining(monClient,baseUrl)
    }
    @Test
    fun localUpdateTraining() = runBlockingTest{

        Assert.assertEquals(1,subject.insertUpdate(monTraining))
        verify(local).updateInsert(monTraining)
    }
    @Test
    fun deleteTraining() = runBlockingTest{
        Assert.assertEquals(1,subject.delete(monTraining))
        verify(local).delete(monTraining)
    }
    @After
    fun close(){
        MockWebServer().shutdown()
    }
}