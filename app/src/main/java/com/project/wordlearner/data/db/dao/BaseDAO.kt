package com.project.wordlearner.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDAO<T> {

    // TODO: make suspend
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(values: List<T>)

    @Delete
    suspend fun delete(value: T): Int

    @Delete
    suspend fun deletes(values: List<T>)

    @Update
    suspend fun update(value: T): Int
}