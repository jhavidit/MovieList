package com.jhavidit.movielisting

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jhavidit.movielisting.api.MovieListingAPI
import com.jhavidit.movielisting.api.moviedetail.MovieDetailAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideHttpClient(authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
                          chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeout = 60, unit = TimeUnit.SECONDS)
            .addInterceptor(authorizationHeaderInterceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(timeout = 60, unit = TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieListingAPI(retrofit: Retrofit): MovieListingAPI {
        return retrofit.create(MovieListingAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailAPI(retrofit: Retrofit): MovieDetailAPI {
        return retrofit.create(MovieDetailAPI::class.java)
    }

}