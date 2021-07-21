package com.mindorks.framework.mvvm.view

import com.mindorks.framework.mvvm.TrainingViewModel
import com.nhaarman.mockitokotlin2.whenever
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServicesinter
import data.repository.TrainingRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class ViewmodelTest {
    @Mock
    lateinit var repository: TrainingRepository

    @Mock
    lateinit var remote: TrainingServicesinter

    @Mock
    lateinit var local: TrainingDao

    @Mock
    lateinit var viewModel: TrainingViewModel

    @Mock
    lateinit var maListe: List<Training>

    @Mock
    lateinit var monTraining: Training

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        TrainingRepository(local, remote)
        runBlocking {
            whenever(repository.getTraining(false)).thenReturn(maListe)
        }
    }

    @Test
    fun TestViewmodel(){

    }

    /*@Test
    fun TestgetJSON() = runBlockingTest { //TODO a deplacer temporaire

        val objtest2 =  JSONObject("{trainings : [{name:training1,id:1}, {name:training2,id:2}]}");
        val ourArray : JSONArray = objtest2.getJSONArray("trainings")
        // fetch JSONArray named users
        val userArray : JSONArray = objtest2.getJSONArray("trainings")

        val NamesTrainings : ArrayList<String> = ArrayList()
        val IdTrraining : ArrayList<String> = ArrayList()
        val ListTraining : ArrayList<Training> = ArrayList()
        var tmpTraining : Training = Training(1, "vttDelete")
        // implement for loop for getting users list data
        for (j in 0 until userArray.length()) {
            // create a JSONObject for fetching single user data
            val userDetail: JSONObject = userArray.getJSONObject(j)

            // fetch email and name and store it in arraylist
            NamesTrainings.add(userDetail.getInt("id").toString());
            tmpTraining.training_id=(userDetail.getInt("id"))
            IdTrraining.add(userDetail.getString("name"))
            tmpTraining.name=(userDetail.getString("name"))
            ListTraining.add(tmpTraining)
            println(NamesTrainings.toString()+"<<<<<<<<<<<"+"\n")
            println(IdTrraining.toString()+">>>>>>>>>"+"\n"+"\n"+"\n")
            println(ListTraining.toString()+"\n")
           // print(userArray.toString()+"><><><><><"+userArray.length().toString()+"\n")
        // emailIds.add(userDetail.getString("name"));
            // create a object for getting contact data from JSONObject
        }



    }*/
}