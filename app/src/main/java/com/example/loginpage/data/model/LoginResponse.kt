package com.example.loginpage.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val status: Boolean,
    val message: String,
    @SerialName("data")
    val `data`: LoginData,
    val token: String
)
@Serializable
data class LoginData(
    val id: Int,
    val name: String,
    val email: String
)