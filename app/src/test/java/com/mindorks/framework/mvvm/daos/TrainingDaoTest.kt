package com.mindorks.framework.mvvm.daos

import data.daos.TrainingDao
import data.db.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.Test

@ExperimentalCoroutinesApi
class TrainingDaoTest : DaoTest<TrainingDao>() {
    override fun buildSubject(db: AppDatabase): TrainingDao = db.getTrainingDao()






}