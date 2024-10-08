package com.example.loginpage.data.data_source

import com.example.loginpage.data.model.ListRestaurants
import com.example.loginpage.data.model.LoginResponse
import com.example.loginpage.data.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("sign_up")
    @FormUrlEncoded
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignUpResponse>

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("skip") skip: Int = 0,
        @Query("search") search: String? = null
    ): Response<ListRestaurants>
}


