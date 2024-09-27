package com.example.loginpage.presentation.screens.signUp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.domain.usecase.SignUpUseCase
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
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun updateName(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName)
    }

    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }


    fun signUp() {
        viewModelScope.launch(IO) {
                _uiState.value = _uiState.value.copy(signUpState = DataState.Loading)

            signUpUseCase(
                _uiState.value.name,
                _uiState.value.email,
                _uiState.value.password
            ).collect { response ->
                when (response) {
                    is DataState.Success -> {
                        withContext(Main) {
                            _uiState.value = _uiState.value.copy(signUpState = DataState.Success(response.data))
                        }
                        Log.d("SignUpViewModel", "Sign Up Success")
                    }
                    is DataState.Error -> {
                        withContext(Main) {
                            _uiState.value = _uiState.value.copy(signUpState = DataState.Error(response.message))
                        }
                        Log.d("SignUpViewModel", "Sign Up Failed: ${response.message}")
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
