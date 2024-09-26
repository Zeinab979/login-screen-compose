package com.example.loginpage.domain.usecase
import com.example.loginpage.domain.repository.AuthRepository


class SignUpUseCase (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String)=
         repository.signUp(name, email, password)
}
