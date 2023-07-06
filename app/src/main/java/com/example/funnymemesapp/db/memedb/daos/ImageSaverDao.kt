package com.example.funnymemesapp.db.memedb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.funnymemesapp.db.memedb.entity.ImageSaver

@Dao
interface ImageSaverDao {

    @Insert
    suspend fun insertImage(image: ImageSaver)

    @Query("SELECT * from imagesaver limit 1")
    suspend fun getSavedImage(): ImageSaver?
}