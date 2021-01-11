package com.github.ahmadriza.mvvmboilerplate.di

import android.content.Context
import android.content.SharedPreferences
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.data.local.SharedPreferenceHelper
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

//    remote

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


//    Local

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences
            = context.getSharedPreferences("example-session", 0)

    @Singleton
    @Provides
    fun providePreferenceHelper(sharedPreferences: SharedPreferences): SharedPreferenceHelper
            = SharedPreferenceHelper(sharedPreferences)

    @Singleton
    @Provides
    fun provideLocalDataSource(helper: SharedPreferenceHelper): LocalDataSource
    = LocalDataSource(helper)


    @Singleton
    @Provides
    fun provideLoginRepository(
        localDataSource: LocalDataSource
    ) : MainRepository = MainRepository(localDataSource)


    /*

     Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
     */

}