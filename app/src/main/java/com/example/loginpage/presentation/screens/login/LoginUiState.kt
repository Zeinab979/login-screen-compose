package com.example.loginpage.presentation.screens.login

import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.util.DataState

data class LoginUiState(
   val  email:String="",
   val  password:String="",
   val loginState: DataState<LoginResponse>
   = DataState.Loading
)
