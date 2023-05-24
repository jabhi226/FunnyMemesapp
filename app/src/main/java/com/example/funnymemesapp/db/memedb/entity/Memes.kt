package com.example.funnymemesapp.db.memedb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Memes(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("meme_id") val meme_id: Int,
    @ColumnInfo("postLink") @SerializedName("postLink") var postLink: String? = null,
    @ColumnInfo("subreddit") @SerializedName("subreddit") var subreddit: String? = null,
    @ColumnInfo("title") @SerializedName("title") var title: String? = null,
    @ColumnInfo("url") @SerializedName("url") var url: String? = null,
    @ColumnInfo("nsfw") @SerializedName("nsfw") var nsfw: Boolean? = null,
    @ColumnInfo("spoiler") @SerializedName("spoiler") var spoiler: Boolean? = null,
    @ColumnInfo("author") @SerializedName("author") var author: String? = null,
    @ColumnInfo("ups") @SerializedName("ups") var ups: Int? = null,
    @ColumnInfo("preview") @SerializedName("preview") var preview: ArrayList<String> = arrayListOf()
)