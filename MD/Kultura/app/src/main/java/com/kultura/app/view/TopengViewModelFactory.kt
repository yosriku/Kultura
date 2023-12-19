package com.kultura.app.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.view.search.SearchTopengViewModel


class SearchTopengViewModelFactory(private val repository: TopengRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchTopengViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchTopengViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}