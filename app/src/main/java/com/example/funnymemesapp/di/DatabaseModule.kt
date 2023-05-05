package com.example.funnymemesapp.di

import android.content.Context
import androidx.room.Room
import com.example.funnymemesapp.db.memedb.MemeDatabase
import com.example.funnymemesapp.db.memedb.daos.MemesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideMemesDatabase(@ApplicationContext context: Context): MemeDatabase {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            MemeDatabase::class.java,
//            MemeDatabase.MEME_DB_NAME
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    fun provideMemesDao(db: MemeDatabase): MemesDao {
//        return db.memesDao
//    }

}

