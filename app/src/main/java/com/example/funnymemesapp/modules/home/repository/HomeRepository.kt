package com.example.funnymemesapp.modules.home.repository

import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.db.memedb.entity.Memes
import com.example.funnymemesapp.modules.home.models.ui.MemeModels

interface HomeRepository {

    suspend fun getMeme():  CommonResponse<ArrayList<MemeModels>>

    suspend fun getStoredMeme():  CommonResponse<ArrayList<MemeModels>>

    suspend fun saveMeme(memes: Memes): CommonResponse<String>
}