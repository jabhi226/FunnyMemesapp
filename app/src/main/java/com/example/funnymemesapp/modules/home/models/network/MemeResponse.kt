package com.example.funnymemesapp.modules.home.models.network

import com.example.funnymemesapp.db.memedb.entity.Memes
import com.google.gson.annotations.SerializedName

data class MemeResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("memes") var memes: ArrayList<Memes> = arrayListOf()

)