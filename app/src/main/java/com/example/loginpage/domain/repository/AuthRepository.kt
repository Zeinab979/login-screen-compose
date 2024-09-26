package com.example.loginpage.domain.repository

import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.data.model.SignUpResponse
import com.example.loginpage.util.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Flow<DataState<SignUpResponse>>
    suspend fun login(email: String, password: String): Flow<DataState<LoginResponse>>
}
