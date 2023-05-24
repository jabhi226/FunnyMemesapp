package com.example.funnymemesapp.modules.home.repository

import com.example.funnymemesapp.db.memedb.MemeDatabase
import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.helper.HomeResponseConverter
import com.example.funnymemesapp.db.memedb.entity.Memes
import com.example.funnymemesapp.modules.home.models.ui.MemeModels
import com.example.funnymemesapp.network.NetworkApiService

class HomeRepositoryImpl(
    private val networkApiService: NetworkApiService,
    private val memesDb: MemeDatabase,
    private val homeResponseConverter: HomeResponseConverter
) : HomeRepository {

    override suspend fun getMeme(): CommonResponse<ArrayList<MemeModels>> {
        return homeResponseConverter.getMemeResponse(networkApiService.getMeme())
    }

    override suspend fun getStoredMeme(): CommonResponse<ArrayList<MemeModels>> {
        return homeResponseConverter.getStoredMemeResponse(memesDb.memesDao.getAllSavedMemes())
    }

    override suspend fun saveMeme(memes: Memes): CommonResponse<String> {
        return homeResponseConverter.saveMemeResponse(memesDb.memesDao.insertMeme(memes))
    }
}