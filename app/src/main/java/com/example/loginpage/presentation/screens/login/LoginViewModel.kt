package com.example.loginpage.presentation.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.domain.usecase.LoginUseCase
import com.example.loginpage.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _loginState: MutableStateFlow<DataState<LoginResponse>> =
        MutableStateFlow(DataState.Idle)
    val loginState: StateFlow<DataState<LoginResponse>> = _loginState

    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun login() {
        viewModelScope.launch(IO) {
            withContext(Main) {
                _uiState.value = _uiState.value.copy(loginState = DataState.Loading)
            }

            loginUseCase(_uiState.value.email, _uiState.value.password).collect { response ->
                _loginState.value = response
                when (response) {
                    is DataState.Success -> {
                        withContext(Main) {
                            _uiState.value = _uiState.value.copy(loginState = DataState.Success(response.data))
                        }
                        Log.d("LoginViewModel", "Login Success")
                    }
                    is DataState.Error -> {
                        withContext(Main) {
                            _uiState.value = _uiState.value.copy(loginState = DataState.Error(response.message))
                        }
                        Log.d("LoginViewModel", "Login Failed: ${response.message}")
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Idle -> {
                    }
                }
            }
        }
    }
}
