package com.project.wordlearner.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.wordlearner.data.db.converter.IntListConverter
import com.project.wordlearner.data.db.converter.StringListConverter
import com.project.wordlearner.data.db.dao.WordDAO
import com.project.wordlearner.data.models.Word

@Database(entities = [Word::class], version = 1)

@TypeConverters(
    StringListConverter::class,
    IntListConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDAO(): WordDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.applicationContext.packageName + ".db"
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                            }
                        })
                        .createFromAsset("database/word.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
