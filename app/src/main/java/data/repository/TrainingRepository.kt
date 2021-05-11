package data.repository

import data.daos.TrainingDao
import data.db.entities.Training

class TrainingRepository (private val dao: TrainingDao){
    val Trainings = dao.getAllTraining()

    suspend fun insertupdate(training: Training){
        dao.updateInsert(training)
    }

    suspend fun delete(training: Training){
        dao.delete(training)
    }

}