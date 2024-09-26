package com.example.loginpage.domain.usecase

import com.example.loginpage.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String)=
         repository.login(email, password)
}
