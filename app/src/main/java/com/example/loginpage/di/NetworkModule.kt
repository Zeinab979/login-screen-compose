package com.example.loginpage.di

import com.example.loginpage.data.data_source.ApiService
import com.example.loginpage.data.repository.AuthRepositoryImpl
import com.example.loginpage.data.repository.RestaurantsRepositoryImpl
import com.example.loginpage.domain.repository.AuthRepository
import com.example.loginpage.domain.repository.RestaurantsRepository
import com.example.loginpage.domain.usecase.AuthUseCase
import com.example.loginpage.domain.usecase.ListRestaurantsUseCase
import com.example.loginpage.domain.usecase.LoginUseCase
import com.example.loginpage.domain.usecase.RestaurantDetailsUseCase
import com.example.loginpage.domain.usecase.RestaurantUseCase
import com.example.loginpage.domain.usecase.SignUpUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://android-training.appssquare.com/api/")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
             .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase = AuthUseCase(
        loginUseCase = LoginUseCase(authRepository),
        signUpUseCase = SignUpUseCase(authRepository)
    )

    @Provides
    @Singleton
    fun provideRestaurantsRepository(apiService: ApiService) : RestaurantsRepository{
        return RestaurantsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRestaurantsUseCase(restaurantsRepository: RestaurantsRepository): RestaurantUseCase=
        RestaurantUseCase(
            listRestaurantsUseCase = ListRestaurantsUseCase(restaurantsRepository),
            restaurantDetailsUseCase = RestaurantDetailsUseCase(restaurantsRepository)
        )

}
