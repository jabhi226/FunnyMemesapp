package com.example.funnymemesapp.db.memedb.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.BLOB
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageSaver(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val id: Int?,
    @ColumnInfo("image", typeAffinity = BLOB) val image: Bitmap?,
    @ColumnInfo("image_uri_string") val imageUriString: String?,
)