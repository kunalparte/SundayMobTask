package com.example.kunalparte.sundaymobtask.Users.api

import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.utils.Consts
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    fun getUsers():Call<List<UserModel>>
}