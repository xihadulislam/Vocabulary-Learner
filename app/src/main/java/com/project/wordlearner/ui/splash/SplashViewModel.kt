package com.project.wordlearner.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.wordlearner.common.getToday
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.models.WordDemo
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    private val wordRepo: WordRepo,
    private val appSharedPref: AppSharedPref
) : ViewModel() {


    fun getTodayWords() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!appSharedPref.getTodayDate().equals(getToday(), true)) {
                appSharedPref.setTodayWordList(Gson().toJson(wordRepo.getTodayWords()))
                appSharedPref.setTodayDate(getToday())

                Log.d(TAG, "getTodayWords: " + getToday())
            } else {
                Log.d(TAG, "getTodayWords: call")
            }
        }
    }


    fun storeQBFromLocal(list: MutableList<WordDemo>) {
        viewModelScope.launch(Dispatchers.IO) {


            list.shuffle()

            val newList = mutableListOf<Word>()
            list.forEach { word ->

                var flg = false
                val pro = mutableListOf<String>()
                word.pron.forEach {

                    if (it == null) {
                        flg = true
                    }
                }

                if (word.en.equals(word.bn, true)) flg = true

                if (word.bn_synonyms.isEmpty() && word.en_synonyms.isEmpty()) flg = true

                if (!flg) {
                    val w = Word(
                        word.pron,
                        word.bn,
                        word.en,
                        word.bn_synonyms,
                        word.en_synonyms,
                        word.sentences
                    )
                    newList.add(w)
                }

            }

            Log.d(TAG, "storeQBFromLocal: newList " + newList.size)
            Log.d(TAG, "storeQBFromLocal: old list  " + list.size)
            wordRepo.storeAllWord(newList)
        }
    }


    companion object {
        private const val TAG = "SplashViewModel"
    }
}