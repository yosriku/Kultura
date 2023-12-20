package com.kultura.app.data.repository

import com.kultura.app.data.remote.ApiService
import com.kultura.app.data.response.DetailTopengResponse
import com.kultura.app.data.response.TopengResponse

class TopengRepository(private val apiService : ApiService) {

    suspend fun getTopeng() : TopengResponse {
        return apiService.getTopeng()
    }

    suspend fun getDetailTopeng(id: String) : DetailTopengResponse {
        return apiService.getDetailTopeng(id)
    }

}