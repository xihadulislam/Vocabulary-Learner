package com.project.wordlearner.data.repositories

import androidx.lifecycle.LiveData
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Question


class QuestionRepo(private val appDatabase: AppDatabase) {
    private val questionDAO = appDatabase.questionDAO()

    suspend fun storeQuestions(math: MutableList<Question>) {
        return questionDAO.inserts(math)
    }

    suspend fun updateQuestions(math: Question):Long {
        return questionDAO.insert(math)
    }

    fun getQuestions(): LiveData<List<Question>> {
        return appDatabase.questionDAO().getQuestions()
    }

    fun getQuestion(): Question? {
        var qus : Question?=null
        val list:MutableList<Question> = appDatabase.questionDAO().getQuestionsList().toMutableList()
//        for ((index, element) in list.withIndex()) {
//            if (!element.isCompleted)  {
//                qus = list[index-1]
//                break
//            }
//        }

        list.shuffle()

        return list[0]
    }


    fun getQuestionById(id: Int): Question {
        return appDatabase.questionDAO().getQuestionById(id)
    }



}