package com.kultura.app.data.remote

import com.kultura.app.data.response.TopengResponse
import retrofit2.http.GET

interface ApiService {

    @GET("topeng")
    suspend fun getTopeng() : TopengResponse

//    @Multipart
//    @POST("post")
//    suspend fun uploadImage(
//
//    ):

}