package com.anangkur.synrgychapter3.data.datasource.remote.retrofit

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TMDB_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjliZmIwZTgzZGUyYTRhZmIxN2MxNTdjY2IyNTRmMyIsInN1YiI6IjViZWFmNjExMGUwYTI2M2JmMzA1N2I4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MrSIS0bBQ6yG-KQIeDGTxgnFHd2n9pcKRw38Z-rlpao"

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(provideOkhttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .build()
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideTMDBService(): TMDBService {
    return provideRetrofit().create(TMDBService::class.java)
}