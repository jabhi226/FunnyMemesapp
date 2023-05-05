package com.example.funnymemesapp.db.memedb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.funnymemesapp.modules.home.models.network.Memes

@Dao
interface MemesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeme(a: Memes): Long

    @Query("select * from memes limit 10")
    suspend fun getAllSavedMemes(): List<Memes>?

}