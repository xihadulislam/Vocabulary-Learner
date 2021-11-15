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


    @Query("SELECT * FROM word WHERE pron LIKE :search OR en LIKE :search ")
    fun search(search: String): List<Word>

    // @Query("SELECT * FROM word WHERE pron LIKE '%' || :param || '%'  OR  en LIKE '%' || :param || '%'  Limit 20")
    @Query("SELECT * FROM word WHERE en LIKE:param or  en LIKE  :param || '%'  Limit 20")
    fun searchItOnEnglish(param: String?): List<Word>

   @Query("SELECT * FROM word WHERE bn LIKE:param or  bn LIKE  :param || '%'  Limit 20")
    fun searchItOnBangla(param: String?): List<Word>


}
