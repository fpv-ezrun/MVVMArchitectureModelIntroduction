package data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.daos.TrainingDao
import data.db.entities.Training

@Database(
    entities = [Training::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

        abstract fun getTrainingDao() : TrainingDao

        companion object{

            @Volatile //visible pour tous les threads
            private var instance: AppDatabase? = null
            private val Lock = Any() //pour être sur qu'il n'y est pas deux instances de la database

            operator fun invoke(context: Context) = instance ?: synchronized(Lock){
                instance?:buildDatabase(context).also { instance = it }
            }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"DataBase.db").build()

        }
}