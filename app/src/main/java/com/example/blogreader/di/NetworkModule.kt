package com.example.blogreader.di

import android.content.Context
import com.example.blogreader.BlogReader
import com.example.blogreader.data.BlogRepositoryImpl
import com.example.blogreader.data.web.BlogApi
import com.example.blogreader.domain.repositories.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://blog.vrid.in/"

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context):BlogReader{
        return context as BlogReader
    }

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRetrofit(client : OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30L,java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30L,java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogApi(retrofit: Retrofit): BlogApi {
        return retrofit.create(BlogApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBlogRepository(blogApi: BlogApi): BlogRepository {
        return BlogRepositoryImpl(blogApi)
    }
}