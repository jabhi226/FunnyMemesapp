package com.example.funnymemesapp.network

import com.example.funnymemesapp.modules.home.models.network.MemeResponse
import retrofit2.http.GET

interface NetworkApiService {

    @GET("gimme/5")
    suspend fun getMeme(): NetworkResponse<MemeResponse, Error>

}