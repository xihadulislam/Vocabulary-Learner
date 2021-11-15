package com.project.wordlearner.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.wordlearner.R
import com.project.wordlearner.common.AppConstants
import com.project.wordlearner.common.ImageUtil
import com.project.wordlearner.data.models.Word
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val wordRepo: WordRepo, private val appSharedPref: AppSharedPref) :
    ViewModel() {

    private val _searchFetchSuccess = MutableLiveData<List<Word>>()
    val searchFetchSuccess: LiveData<List<Word>> = _searchFetchSuccess

    fun searchIt(txt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (appSharedPref.getSearchType().equals(AppConstants.E_TO_B, true)) {
                val list = wordRepo.searchItOnEnglish(txt)
                _searchFetchSuccess.postValue(list)
            } else {
                val list = wordRepo.searchItOnBangla(txt)
                _searchFetchSuccess.postValue(list)
            }

        }

    }


}