package com.github.ahmadriza.mvvmboilerplate.di

import android.content.Context
import android.content.SharedPreferences
import com.github.ahmadriza.mvvmboilerplate.BuildConfig
import com.github.ahmadriza.mvvmboilerplate.data.local.LocalDataSource
import com.github.ahmadriza.mvvmboilerplate.data.local.SharedPreferenceHelper
import com.github.ahmadriza.mvvmboilerplate.data.remote.MainService
import com.github.ahmadriza.mvvmboilerplate.data.remote.RemoteDataSource
import com.github.ahmadriza.mvvmboilerplate.data.remote.interceptor.AuthInterceptor
import com.github.ahmadriza.mvvmboilerplate.data.remote.interceptor.JsonInterceptor
import com.github.ahmadriza.mvvmboilerplate.data.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private const val BASE_URL = "http://massageapp.reeyn.com/public/"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        jsonInterceptor: JsonInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(jsonInterceptor)
        if (BuildConfig.DEBUG) {
            builder
                .addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
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

    @Provides
    fun provideJsonInterceptor(): JsonInterceptor = JsonInterceptor()

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //  Local

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("example-session", 0)

    @Singleton
    @Provides
    fun providePreferenceHelper(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): SharedPreferenceHelper =
        SharedPreferenceHelper(sharedPreferences, gson)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        helper: SharedPreferenceHelper,
        authInterceptor: AuthInterceptor
    ): LocalDataSource =
        LocalDataSource(helper, authInterceptor)


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