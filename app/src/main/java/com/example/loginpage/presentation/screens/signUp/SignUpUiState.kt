package com.example.loginpage.presentation.screens.signUp

import com.example.loginpage.data.model.SignUpResponse
import com.example.loginpage.util.DataState

data class SignUpUiState(
    val name:String="",
    val email:String="",
    val password :String="",
    val signUpState: DataState<SignUpResponse> = DataState.Idle
)
