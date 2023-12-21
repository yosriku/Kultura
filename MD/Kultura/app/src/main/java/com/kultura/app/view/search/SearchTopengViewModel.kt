package com.kultura.app.view.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.data.response.TopengItem
import kotlinx.coroutines.launch

class SearchTopengViewModel(private val repository: TopengRepository) : ViewModel() {

    private val _listTopeng = MutableLiveData<List<TopengItem>>()
    val listTopeng: LiveData<List<TopengItem>> = _listTopeng


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getTopeng()
    }


    private fun getTopeng() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val topeng = repository.getTopeng().topeng
                _listTopeng.postValue(topeng)
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "Unknown error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }

}