package com.example.funnymemesapp.modules.home.helper

import com.example.funnymemesapp.modules.core.models.CommonResponse
import com.example.funnymemesapp.modules.home.models.network.MemeResponse
import com.example.funnymemesapp.modules.home.models.network.Memes
import com.example.funnymemesapp.network.NetworkResponse

class HomeResponseConverter {
    fun getMemeResponse(meme: NetworkResponse<MemeResponse, Error>): CommonResponse<ArrayList<Memes>> {
        return when (meme) {
            is NetworkResponse.Success -> {
                CommonResponse.Success(meme.body.memes)
            }
            else -> {
                CommonResponse.Error("Something went wrong")
            }
        }
    }

    fun getStoredMemeResponse(allSavedMemes: List<Memes>?): CommonResponse<java.util.ArrayList<Memes>> {
        return if (allSavedMemes.isNullOrEmpty()) {
            CommonResponse.Success(arrayListOf())
        } else {
            CommonResponse.Success(allSavedMemes as java.util.ArrayList<Memes>)
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