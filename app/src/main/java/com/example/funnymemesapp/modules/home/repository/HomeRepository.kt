package com.example.funnymemesapp.modules.home.repository

import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.models.network.Memes
import com.example.funnymemesapp.network.NetworkResponse

typealias SaarthiGenericResponse<T> = NetworkResponse<T, Error>

interface HomeRepository {

    suspend fun getMeme():  CommonResponse<ArrayList<Memes>>

    suspend fun getStoredMeme():  CommonResponse<ArrayList<Memes>>

    suspend fun saveMeme(memes: Memes): CommonResponse<String>
}