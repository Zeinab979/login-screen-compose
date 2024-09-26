package com.example.loginpage.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val status: Boolean,
    val message: String,
    @SerialName("data")
    val `data`: SignUpData
)
@Serializable
data class SignUpData(
    val id: Int,
    val name: String,
    val email: String
)