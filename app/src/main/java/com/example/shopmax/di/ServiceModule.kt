package com.example.shopmax.di

import com.example.shopmax.data.ShipmentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {


    @ViewModelScoped
    @Provides
    fun provideShipmentsService(retrofit: Retrofit): ShipmentService =
        retrofit.create(ShipmentService::class.java)

}