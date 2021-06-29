package com.mindorks.framework.mvvm.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.common.truth.Truth.assertThat
//import com.sun.tools.javac.util.DefinedBy.Api
import data.daos.TrainingDao
import data.db.AppDatabase
import data.db.entities.Training
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
abstract class DaoTest<T : Any> {
    @get:Rule
    var instantTasKExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: AppDatabase
    private lateinit var dao: TrainingDao
    lateinit var subject: T
    abstract fun buildSubject(db: AppDatabase): T
    private val trainingVtt =
        Training(1, "vttDelete")//instanciation d'un training pour faire les tests


    @Before
    open fun setup() {

//        subject = buildSubject(database)
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getTrainingDao()

        dao.updateInsert(trainingVtt)
        //////////////////////////////////////////////
        val url = "localhost:3000/trainings"
        val jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                print("Response: " + response.toString())
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        ///////////////////////////////////////////////
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test //test de la fonction de récupération
    fun getTraining() = runBlockingTest {
        val allTraining = dao.getAllTraining()
        print(allTraining.toString() + "<<<<<<<<<<\n") //affichage de ce qu'on a recuperer
        //print(allTraining.value.toString()+"++++++++\n")
        //assertThat(allTraining.toString())
    }

    @Test //test de la fonction d'insertion
    fun insertTraining() = runBlockingTest {
        /* val trainingVtt = Training(2,"vtt")
        val returningValue = dao.updateInsert(trainingVtt)
        */
        val allTraining = dao.getAllTraining()
        // print("valeur de retour<<<$returningvalue>>>\n")
        //print(allTraining.toString()+"<<<<<<<<<<\n")
        // print(allTraining.value.toString()+"++++++++\n")

        // assertThat(allTraining.toString())
        assertThat(allTraining).contains(trainingVtt)


    }

    @Test //test de la fonction de suppression
    fun deleteTraining() = runBlockingTest {
        /*val trainingVtt = Training(1,"vttdelete")
        dao.updateInsert(trainingVtt)*/
        dao.delete(trainingVtt)
        val allTraining = dao.getAllTraining()

        assertThat(allTraining).doesNotContain(trainingVtt)
    }

    @Test
    open fun testAPIJava() {
        var api = testAPIold()
        print("Reponse: <<<<<<<<<<"+api.get("http://localhost:3000/trainings")+">>>>>>>>>>>>\n")
    }
}

/*
    @Test //test de la fonction de suppression
    fun testApi()= runBlockingTest {
        val queue = Volley.newRequestQueue(ApplicationProvider.getApplicationContext())
        //val url = "localhost:3000/trainings"
        val url = "http://www.google.fr"
        var toto = 0
        var cpt = 0
        print("avant request api \n")
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                print("Response is: ${response.substring(0, 500)}\n")
                toto = 1
            },
            { print("That didn't work!\n")
                toto = 2 })
        print("après request api :"+stringRequest+"\n")
// Add the request to the RequestQueue.
        queue.add(stringRequest)
}
     //@Test
     fun main() {
         print("\n")
         val url = "localhost:3000/trainings"
         val jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(
             Request.Method.GET, url, null,
             { response ->
                 print("Response: " + response.getString("trainingId"))
             },
             { error ->
                 print("fail to get informations from webservices")
             // TODO: Handle error
             }
         )

         print(jsonObjectRequest.toString())

    }
*/
     // Instantiate the RequestQueue.

