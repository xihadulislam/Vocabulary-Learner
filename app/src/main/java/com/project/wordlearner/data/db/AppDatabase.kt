package com.project.wordlearner.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.wordlearner.data.db.dao.QuestionDAO
import com.project.wordlearner.data.models.Question

@Database(entities = [Question::class], version = 1)

@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDAO(): QuestionDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.applicationContext.packageName + ".DB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
