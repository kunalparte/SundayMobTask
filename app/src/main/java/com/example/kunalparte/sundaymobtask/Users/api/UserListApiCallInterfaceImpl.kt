package com.example.kunalparte.sundaymobtask.Users.api

import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListApiCallInterfaceImpl constructor() : UserListInterface.UserListApiCallInterface {


    override fun requestUserListFromApi(userListApiResponseInterface: UserListInterface.UserListApiResponseInterface) {
        val userApiCall = RetrofitClient.getRetrofitClient()!!.create(ApiInterface::class.java)
        userApiCall.getUsers().enqueue(object : Callback<List<UserModel>>{
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.isSuccessful){
                    response.body()?.let { userListApiResponseInterface.onApiCallSuccess(it) }
                }
            }
        })
    }


}