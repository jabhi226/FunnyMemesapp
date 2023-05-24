package com.example.funnymemesapp.modules.home.models.ui

import com.example.funnymemesapp.db.memedb.entity.Memes

data class MemeModels(
    val meme: Memes
) {
    var isFavorite = false
}