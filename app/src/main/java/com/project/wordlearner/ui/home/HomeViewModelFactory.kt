package com.project.wordlearner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.WordRepo

class HomeViewModelFactory(
    private val wordRepo: WordRepo,
    private  val  appSharedPref: AppSharedPref
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel( wordRepo,appSharedPref) as T
    }
}