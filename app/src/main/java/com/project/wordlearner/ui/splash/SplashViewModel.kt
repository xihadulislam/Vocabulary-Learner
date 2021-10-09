package com.project.wordlearner.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.wordlearner.data.models.Data
import com.project.wordlearner.data.repositories.DataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataRepo: DataRepo
) : ViewModel() {


    fun storeQBFromLocal(qus: List<Data>) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepo.storeQuestions(qus)
        }
    }


    companion object {
        private const val TAG = "SplashViewModel"
    }
}