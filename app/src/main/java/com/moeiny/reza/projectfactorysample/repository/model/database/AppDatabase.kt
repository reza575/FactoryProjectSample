package com.moeiny.reza.projectfactorysample.repository.model.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moeiny.reza.optustest.repository.database.entitiy.DecisionEntity
import com.moeiny.reza.projectfactorysample.repository.model.database.dao.DecisionDao


@Database(entities = [(DecisionEntity::class)], version = 1, exportSchema = false)

public abstract class AppDatabase : RoomDatabase() {

    abstract fun DecissionDao(): DecisionDao


    companion object {

        private var instance: AppDatabase? = null
        public var DB_NAME = "Decisions"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                    .execute()
        }
    }

}

    class PopulateDbAsyncTask(db: AppDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val userDao = db?.DecissionDao()

    override fun doInBackground(vararg p0: Unit?) {
       // UserDao?.insert(UserEntity("", "","","","","",""))

      }
    }

