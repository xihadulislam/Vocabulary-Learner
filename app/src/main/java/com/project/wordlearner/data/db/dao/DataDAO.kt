package com.project.wordlearner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.project.wordlearner.data.models.Data

@Dao
interface DataDAO : BaseDAO<Data> {

    @Query("select * from Data ")
    fun getQuestions(): LiveData<List<Data>>

    @Query("select * from data order by id ASC")
    fun getQuestionsList(): List<Data>

    @Query("select * from data  where id = :id order by id ASC")
    fun getQuestionById(id: Int): Data
}
