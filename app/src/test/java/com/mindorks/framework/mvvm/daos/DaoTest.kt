package com.mindorks.framework.mvvm.daos

import androidx.room.ExperimentalRoomApi
import androidx.room.Room
import data.daos.TrainingDao
import data.db.AppDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider
import data.db.entities.Training
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import java.time.Instant

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
abstract class DaoTest<T : Any>
{
    @get:Rule
    var instantTasKExecutorRule = InstantTaskExecutorRule()



    private lateinit var database: AppDatabase
    private lateinit var dao:TrainingDao
    lateinit var subject: T
    abstract fun buildSubject(db: AppDatabase): T


    @Before
    open fun setup()
    {

//        subject = buildSubject(database)
        database=Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getTrainingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTraining()=runBlockingTest{
        val trainingVtt = Training(2,"vtt")
        val returningValue = dao.updateInsert(trainingVtt)

        val allTraining = dao.getAllTraining().getOrAwaitValue()
       // print("valeur de retour<<<$returningvalue>>>\n")
        //print(allTraining.toString()+"<<<<<<<<<<\n")
       // print(allTraining.value.toString()+"++++++++\n")

       // assertThat(allTraining.toString())
        assertThat(allTraining).contains(trainingVtt)


    }
    @Test
    fun deleteTraining()= runBlockingTest {
        val trainingVtt = Training(1,"vttdelete")
        dao.updateInsert(trainingVtt)
        dao.delete(trainingVtt)
        val allTraining = dao.getAllTraining().getOrAwaitValue()

        assertThat(allTraining).doesNotContain(trainingVtt)
    }
}