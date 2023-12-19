package com.kultura.app.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.data.response.DetailTopengResponse
import kotlinx.coroutines.launch

class DetailTopengViewModel (private val repository: TopengRepository) : ViewModel() {

    private val _detailTopeng = MutableLiveData<DetailTopengResponse>()
    val detailTopeng: LiveData<DetailTopengResponse> = _detailTopeng

    fun getDetailTopeng (id: String){
        viewModelScope.launch {
            val detailStory = repository.getDetailTopeng(id)
            _detailTopeng.postValue(detailStory)
        }
    }
}