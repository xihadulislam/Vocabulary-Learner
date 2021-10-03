package com.project.wordlearner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.project.wordlearner.data.models.Question

@Dao
interface QuestionDAO : BaseDAO<Question> {

    @Query("select * from question ")
    fun getQuestions(): LiveData<List<Question>>

    @Query("select * from question order by id ASC")
    fun getQuestionsList(): List<Question>

    @Query("select * from question  where id = :id order by id ASC")
    fun getQuestionById(id: Int): Question
}
