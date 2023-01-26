package com.example.productnutritionapp.di

import com.example.productnutritionapp.base.constants.Constants.API_KEY
import com.example.productnutritionapp.base.constants.Constants.BASE_URL
import com.example.productnutritionapp.base.constants.Constants.HEADER_NAME_KEY
import com.example.productnutritionapp.data.network.repository.NetworkRepository
import com.example.productnutritionapp.data.network.repository.NetworkRepositoryImpl
import com.example.productnutritionapp.data.network.retrofit.RecipeApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { Interceptor { interceptor ->
        val request = interceptor.request().newBuilder()
            .addHeader(HEADER_NAME_KEY, API_KEY).build()
        return@Interceptor interceptor.proceed(request) }
    }
    factory { provideOkHttpClient(get()) }
    factory { provideRecipeApi(get()) }
    single { provideRetrofit(get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}

fun provideRecipeApi(retrofit: Retrofit): RecipeApi =
    retrofit.create(RecipeApi::class.java)