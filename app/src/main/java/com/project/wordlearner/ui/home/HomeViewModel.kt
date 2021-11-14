package com.project.wordlearner.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.wordlearner.common.AppConstants
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val wordRepo: WordRepo,
    private val appSharedPref: AppSharedPref
) : ViewModel() {


    private val words: LiveData<List<Word>> by lazy {
        wordRepo.getWords(1, 9)
    }

    fun getWordList(): LiveData<List<Word>> {
        return words
    }

    fun bookmarkUpdate(word: Word) {

        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.updateWord(word)
            val list = Gson().fromJson(appSharedPref.getTodayWordList(), Array<Word>::class.java)
                .toMutableList()

            list.forEach {
                if (it.id == word.id) {
                    it.isFav = word.isFav
                }
            }
            appSharedPref.setTodayWordList(Gson().toJson(list))
        }
    }

    fun stageUpdate(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.updateWord(word)
            val list = Gson().fromJson(appSharedPref.getTodayWordList(), Array<Word>::class.java)
                .toMutableList()

            var seletcted :Word? = null

            list.forEach {
                if (it.id == word.id) {
                    if (word.stage.equals(AppConstants.I_KNOW, true)) {
                        seletcted = it
                    } else {
                        it.stage = word.stage
                    }
                }
            }
            seletcted?.let {
                list.remove(it)
            }

            appSharedPref.setTodayWordList(Gson().toJson(list))
        }
    }


}