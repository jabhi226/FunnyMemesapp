package com.example.funnymemesapp.di

import android.content.Context
import androidx.room.Room
import com.example.funnymemesapp.db.memedb.MemeDatabase
import com.example.funnymemesapp.db.memedb.daos.MemesDao
import com.example.funnymemesapp.modules.home.helper.HomeResponseConverter
import com.example.funnymemesapp.network.NetworkApiService
import com.example.funnymemesapp.network.NetworkResponseAdapterFactory
import com.example.funnymemesapp.modules.home.repository.HomeRepository
import com.example.funnymemesapp.modules.home.repository.HomeRepositoryImpl
import com.example.funnymemesapp.utils.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60 * 5, TimeUnit.SECONDS)
//            .followRedirects(false)
//            .followSslRedirects(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(EndPoints.MEME_IP)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideNetworkApiService(retrofit: Retrofit): NetworkApiService {
        return retrofit.create(NetworkApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMemesDatabase(@ApplicationContext context: Context): MemeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MemeDatabase::class.java,
            MemeDatabase.MEME_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMemesDao(db: MemeDatabase): MemesDao {
        return db.memesDao
    }

    @Provides
    fun provideHomeResponseConverter(): HomeResponseConverter{
        return HomeResponseConverter()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(networkApiService: NetworkApiService, memeDatabase: MemeDatabase, homeResponseConverter: HomeResponseConverter): HomeRepository {
        return HomeRepositoryImpl(networkApiService, memeDatabase, homeResponseConverter)
    }

    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            println("CoroutineException -> ${exception.printStackTrace()}")
        }
    }
}