package com.kultura.app.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.view.detail.DetailTopengViewModel
import com.kultura.app.view.search.SearchTopengViewModel


class SearchTopengViewModelFactory(private val repository: TopengRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchTopengViewModel::class.java) -> {
                SearchTopengViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailTopengViewModel::class.java) -> {
                DetailTopengViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}