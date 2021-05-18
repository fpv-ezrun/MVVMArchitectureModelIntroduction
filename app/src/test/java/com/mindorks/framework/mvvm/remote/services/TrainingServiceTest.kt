package com.mindorks.framework.mvvm.remote.services

import com.mindorks.framework.mvvm.testUtils.ServiceTest
import data.remote.services.TrainingServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TrainingServiceTest : ServiceTest<TrainingServices>() {
    override fun buildSubject(baseUrl: String, client: OkHttpClient): TrainingServices {
        return
    }
    @Test
    fun `fetchTraining() all good`() = runBlockingTest {
        //enqueueSuccessWithResponse("success_get_training.json")
        //assertResponse(getTrainingSessionOutput()) { subject.fetchTraining() }
        assertGET("/product/football-teams/teid/seasons/sid/trainings/trid")
    }
}
