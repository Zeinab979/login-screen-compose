package com.example.loginpage.di

import com.example.loginpage.data.data_source.ApiService
import com.example.loginpage.data.repository.AuthRepositoryImpl
import com.example.loginpage.data.repository.RestaurantsRepositoryImpl
import com.example.loginpage.domain.repository.AuthRepository
import com.example.loginpage.domain.repository.RestaurantsRepository
import com.example.loginpage.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
            .addConverterFactory(GsonConverterFactory.create())
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
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideRestaurantsRepository(apiService: ApiService): RestaurantsRepository {
        return RestaurantsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideListRestaurantsUseCase(restaurantsRepository: RestaurantsRepository): ListRestaurantsUseCase {
        return ListRestaurantsUseCase(restaurantsRepository)
    }

}
