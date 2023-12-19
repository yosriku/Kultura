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

    init {
        getTopeng()
    }


    private fun getTopeng() {
        viewModelScope.launch {
            try {
                val topeng = repository.getTopeng().topeng
                _listTopeng.postValue(topeng)
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "Unknown error occurred")
            } finally {

            }
        }
    }

}