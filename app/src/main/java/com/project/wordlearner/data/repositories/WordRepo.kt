package com.project.wordlearner.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Word
import kotlin.math.min


class WordRepo(private val appDatabase: AppDatabase) {
    private val questionDAO = appDatabase.wordDAO()

    suspend fun storeAllWord(math: List<Word>) {
        return questionDAO.inserts(math)
    }

    suspend fun updateWord(math: Word): Long {
        return questionDAO.insert(math)
    }

    fun getWords(): LiveData<List<Word>> {
        return appDatabase.wordDAO().getWords()
    }

    fun getWords(page: Int, limit: Int): LiveData<List<Word>> {
        return appDatabase.wordDAO().getWordListByLimit()
    }

    fun getWord(): Word {
        val list: MutableList<Word> = appDatabase.wordDAO().getWordList().toMutableList()
        list.shuffle()
        return list[0]
    }


    fun getWordById(id: Long): Word? {
        return appDatabase.wordDAO().getWordById(id)
    }


    suspend fun getTodayWords(): List<Word> {

        val idList = appDatabase.wordDAO().getWordId().toMutableList()

        Log.d(TAG, "getTodayWords: " + idList.size)

        idList.shuffle()
        val ids = idList.subList(0, min(25, idList.size))

        val wordList = mutableListOf<Word>()

        ids.forEach { id ->
            appDatabase.wordDAO().getWordById(id)?.let {
                wordList.add(it)
            }
        }

        return wordList
    }


    companion object {
        private const val TAG = "WordRepo"
    }

}