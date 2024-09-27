package com.example.loginpage.domain.usecase

data class AuthUseCase(
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase
)
