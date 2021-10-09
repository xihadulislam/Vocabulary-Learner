package com.project.wordlearner.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.wordlearner.data.repositories.DataRepo

class SplashViewModelFactory(
    private val dataRepo: DataRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel( dataRepo) as T
    }
}