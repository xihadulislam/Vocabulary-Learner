package com.project.wordlearner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.project.wordlearner.data.models.Word

@Dao
interface WordDAO : BaseDAO<Word> {

    @Query("select * from Word ")
    fun getWords(): LiveData<List<Word>>

    @Query("select * from Word  order by id ASC LIMIT :limit")
    fun getWordListByLimit(limit: Int = 300): LiveData<List<Word>>


    @Query("select * from word order by id ASC Limit 400")
    fun getWordList(): List<Word>

    @Query("select * from word  where id = :id order by id ASC")
    fun getWordById(id: Long): Word?


    @Query("select id from word  ")
    fun getWordId(): List<Long>


}
