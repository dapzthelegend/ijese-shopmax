package com.example.shopmax.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://staging-shopmax.herokuapp.com/api/v1/"



    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }


    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
       val requestTimeout = 60
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .cache(cache)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val original = chain.request()


            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer Mzc3.Qcm2dkW1gcr_rrxK9jw0yTYODs8Ub84CkVO0X3tEQ5Uyy0bAIFCZeZt_SDnF")


            // Adding Authorization token (API Key)
            // Requests will be denied without API key
            val request = requestBuilder.build()
            val result =  chain.proceed(request)

            result
        }


        return httpClient.build()


    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

}