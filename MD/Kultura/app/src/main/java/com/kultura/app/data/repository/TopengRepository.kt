package com.kultura.app.data.repository

import com.kultura.app.data.remote.ApiService
import com.kultura.app.data.response.TopengResponse

class TopengRepository(private val apiService : ApiService) {

    suspend fun getTopeng() : TopengResponse {
        return apiService.getTopeng()
    }

}