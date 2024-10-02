package com.example.loginpage.presentation.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.model.SignUpResponse
import com.example.loginpage.domain.usecase.SignUpUseCase
import com.example.loginpage.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState
    private val _signUpState: MutableStateFlow<DataState<SignUpResponse>> =
        MutableStateFlow(DataState.Idle)
    val signUpState : StateFlow<DataState<SignUpResponse>> = _signUpState
    fun updateName(newName: String) {
        _uiState.update { it.copy(name = newName) }
    }

    fun updateEmail(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun signUp() {
        viewModelScope.launch(IO) {
            signUpUseCase(
                name = _uiState.value.name,
                email = _uiState.value.email,
                password = _uiState.value.password
            ).collect { response ->
                _signUpState.value = response
            }
        }
    }
}
