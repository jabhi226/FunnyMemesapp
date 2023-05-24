package com.example.funnymemesapp.db.memedb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.funnymemesapp.db.memedb.daos.MemesDao
import com.example.funnymemesapp.db.memedb.entity.Memes

@Database(entities = [Memes::class], version = 1, exportSchema = true)
@TypeConverters(MemeDbTypeConverter::class)
abstract class MemeDatabase: RoomDatabase() {
    companion object {
        const val MEME_DB_NAME = "meme_db"
    }

    abstract val memesDao: MemesDao

}