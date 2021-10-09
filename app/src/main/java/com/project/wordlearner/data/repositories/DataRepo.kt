package com.project.wordlearner.data.repositories

import androidx.lifecycle.LiveData
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Data


class DataRepo(private val appDatabase: AppDatabase) {
    private val questionDAO = appDatabase.questionDAO()

    suspend fun storeQuestions(math: List<Data>) {
        return questionDAO.inserts(math)
    }

    suspend fun updateQuestions(math: Data): Long {
        return questionDAO.insert(math)
    }

    fun getQuestions(): LiveData<List<Data>> {
        return appDatabase.questionDAO().getQuestions()
    }

    fun getQuestion(): Data {
        var qus: Data? = null
        val list: MutableList<Data> = appDatabase.questionDAO().getQuestionsList().toMutableList()
//        for ((index, element) in list.withIndex()) {
//            if (!element.isCompleted)  {
//                qus = list[index-1]
//                break
//            }
//        }

        list.shuffle()

        return list[0]
    }


    fun getQuestionById(id: Int): Data {
        return appDatabase.questionDAO().getQuestionById(id)
    }


}