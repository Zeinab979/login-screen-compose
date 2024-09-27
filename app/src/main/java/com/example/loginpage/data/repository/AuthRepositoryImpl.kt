package com.example.loginpage.data.repository

import android.util.Log
import com.example.loginpage.data.data_source.ApiService
import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.data.model.SignUpResponse
import com.example.loginpage.domain.repository.AuthRepository
import com.example.loginpage.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
    ): Flow<DataState<SignUpResponse>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.signUp(name, email, password)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("AuthRepository", "Sign up successful: $it")
                    emit(DataState.Success(it))
                } ?: emit(DataState.Error("Sign up failed: Empty response body"))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Sign up failed: Unknown error"
                Log.e("AuthRepository", errorMessage)
                emit(DataState.Error(errorMessage))
            }
        } catch (e: IOException) {
            emit(DataState.Error("Network error: ${e.localizedMessage ?: "Check your connection"}"))

    } catch (e: Exception) {
            Log.e("AuthRepository", "Sign up failed: ${e.localizedMessage ?: e.message}")
            emit(DataState.Error("Sign up failed: ${e.localizedMessage ?: e.message}"))
        }
    }
        .flowOn(Dispatchers.IO)

    override suspend fun login(
        email: String,
        password: String,
    ): Flow<DataState<LoginResponse>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.login(email, password)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("AuthRepository", "Login successful: $it")
                    emit(DataState.Success(it))
                } ?: emit(DataState.Error("Login failed: Empty response body"))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Login failed: Unknown error"
                Log.e("AuthRepository", errorMessage)
                emit(DataState.Error(errorMessage))
            }
        }
        catch (e: IOException) {
            emit(DataState.Error("Network error: ${e.localizedMessage ?: "Check your connection"}"))
        }
        catch (e: Exception) {
            Log.e("AuthRepository", "Login failed: ${e.localizedMessage ?: e.message}")
            emit(DataState.Error("Login failed: ${e.localizedMessage ?: e.message}"))
        }
    }
        .flowOn(Dispatchers.IO)
}
