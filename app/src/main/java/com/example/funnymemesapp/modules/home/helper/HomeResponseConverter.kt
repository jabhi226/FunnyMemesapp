package com.example.funnymemesapp.modules.home.helper

import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.models.network.MemeResponse
import com.example.funnymemesapp.modules.home.models.network.Memes
import com.example.funnymemesapp.modules.home.models.ui.MemeModels
import com.example.funnymemesapp.network.NetworkResponse

class HomeResponseConverter {
    fun getMemeResponse(meme: NetworkResponse<MemeResponse, Error>): CommonResponse<ArrayList<MemeModels>> {
        return when (meme) {
            is NetworkResponse.Success -> {
                val list = meme.body.memes.map { MemeModels(it) } as ArrayList<MemeModels>
                CommonResponse.Success(list)
            }
            else -> {
                CommonResponse.Error("Something went wrong")
            }
        }
    }

    fun getStoredMemeResponse(allSavedMemes: List<Memes>?): CommonResponse<java.util.ArrayList<MemeModels>> {
        return if (allSavedMemes.isNullOrEmpty()) {
            CommonResponse.Success(arrayListOf())
        } else {
            val list = allSavedMemes.map {
                val m = MemeModels(it)
                m.isFavorite = true
                m
            } as ArrayList<MemeModels>
            CommonResponse.Success(list)
        }
    }

    fun saveMemeResponse(insertMeme: Long): CommonResponse<String> {
        return if (insertMeme == -1L){
            CommonResponse.Error("Something went wrong")
        } else {
            CommonResponse.Success("Meme saved successfully")
        }
    }
}