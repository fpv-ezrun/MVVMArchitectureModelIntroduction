package data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import data.db.entities.Training


@Dao
interface TrainingDao {
    @Insert(onConflict=REPLACE)
    fun updateInsert(training: Training) : Long

    @Delete
    fun delete(training: Training) : Int

    @Query("SELECT * FROM training")
    fun getAllTraining(): List<Training>


    @Query("SELECT * FROM training WHERE training_id = :trainingId")
    fun gettrainingid(trainingId: Int) : Training

    @Insert(onConflict=REPLACE)
    fun updateInserttest(training: Training) : Long
}