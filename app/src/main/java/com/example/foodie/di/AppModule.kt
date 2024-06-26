package com.example.foodie.di

import com.example.foodie.data.datasource.ProductsDataSource
import com.example.foodie.data.repository.ProductsRepository
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.retrofit.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductsRepository(proDataSource: ProductsDataSource): ProductsRepository {
        return ProductsRepository(proDataSource)

    }

    @Provides
    @Singleton
    fun providesProductsDataSource(productApi: ProductApi): ProductsDataSource {
        return ProductsDataSource(productApi)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return ApiUtils.getProductApi()
    }
}