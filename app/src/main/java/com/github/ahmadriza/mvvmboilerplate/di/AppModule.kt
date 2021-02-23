package com.github.ahmadriza.mvvmboilerplate.di

import android.content.Context
import android.content.SharedPreferences
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.data.local.SharedPreferenceHelper
import com.github.ahmadriza.mvvmboilerplate.data.remote.MainService
import com.github.ahmadriza.mvvmboilerplate.data.remote.RemoteDataSource
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */


@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    // remote

    const val baseURl = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(baseURl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: MainService): RemoteDataSource = RemoteDataSource(service)

    //  Local

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("example-session", 0)

    @Singleton
    @Provides
    fun providePreferenceHelper(sharedPreferences: SharedPreferences): SharedPreferenceHelper =
        SharedPreferenceHelper(sharedPreferences)

    @Singleton
    @Provides
    fun provideLocalDataSource(helper: SharedPreferenceHelper): LocalDataSource =
        LocalDataSource(helper)


    // repository

    @Singleton
    @Provides
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MainRepository = MainRepository(localDataSource, remoteDataSource)


    /*

     Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
     */

}