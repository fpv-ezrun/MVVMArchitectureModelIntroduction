package com.mindorks.framework.mvvm.view

import com.mindorks.framework.mvvm.TrainingViewModel
import com.mindorks.framework.mvvm.testUtils.mockReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import data.daos.TrainingDao
import data.db.entities.Training
import data.remote.services.TrainingServices
import data.repository.TrainingRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ViewmodelTest {
    @Mock
    lateinit var repository: TrainingRepository

    @Mock
    lateinit var remote: TrainingServices

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
            whenever(repository.getLocalTraining()).thenReturn(maListe)
        }
    }

    @Test
    fun TestgetLocalTraining() = runBlockingTest {
       // Assert.assertEquals(maListe, viewModel.getLocalTrainingsFromRepository())

        verify(repository).getLocalTraining()
    }
}