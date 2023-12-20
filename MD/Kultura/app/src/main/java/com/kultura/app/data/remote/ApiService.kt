package com.kultura.app.data.remote

import com.kultura.app.data.response.DetailTopengResponse
import com.kultura.app.data.response.ScanResponse
import com.kultura.app.data.response.TopengResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @GET("topeng")
    suspend fun getTopeng() : TopengResponse

    @GET("topeng/{id}")
    suspend fun getDetailTopeng(
        @Path("id") id: String
    ) : DetailTopengResponse

    @Multipart
    @POST("post")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): ScanResponse

}