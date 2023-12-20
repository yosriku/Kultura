package com.kultura.app.data.remote

import com.kultura.app.data.response.DetailTopengResponse
import com.kultura.app.data.response.TopengResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("topeng")
    suspend fun getTopeng() : TopengResponse

    @GET("topeng/{id}")
    suspend fun getDetailTopeng(
        @Path("id") id: String
    ) : DetailTopengResponse

//    @Multipart
//    @POST("post")
//    suspend fun uploadImage(
//
//    ): ScanResponse

}