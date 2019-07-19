package com.example.kunalparte.sundaymobtask.Users.api

import com.example.kunalparte.sundaymobtask.Users.model.UserModel

interface UserListInterface {

    interface UserListApiCallInterface{
        fun requestUserListFromApi( userListApiResponseInterface: UserListApiResponseInterface)
    }
    interface UserListApiResponseInterface{
        fun onApiCallSuccess(user:List<UserModel>)
        fun onApiCallFailure( error: String)
    }
}