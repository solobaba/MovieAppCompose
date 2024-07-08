package com.example.movieappcompose.movie.di

import android.app.Application
import androidx.room.Room
import com.example.movieappcompose.movie.data.local.MovieListDatabase
import com.example.movieappcompose.movie.data.remote.ApiService
import com.example.movieappcompose.movie.data.remote.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi: Moshi? = null

    private fun provideMoshi(): Moshi {
        synchronized(this) {
            return moshi ?: Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi() : ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getClient())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application) : MovieListDatabase {
        return Room.databaseBuilder(
            app,
            MovieListDatabase::class.java,
            "moviedb.db",
        ).build()
    }
}