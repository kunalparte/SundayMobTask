package com.example.kunalparte.sundaymobtask.Users.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kunalparte.sundaymobtask.Users.db.UserDao
import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import kotlinx.coroutines.*

class UserRepository constructor(userDao: UserDao) : UserListInterface.UserListApiResponseInterface {
    val userDao = userDao
    val userListApiCallInterface : UserListApiCallInterfaceImpl by lazy {
        UserListApiCallInterfaceImpl()
    }
    private lateinit var userList : MutableLiveData<List<UserModel>>

    override fun onApiCallSuccess(user: List<UserModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insert(user)
        }
    }

    override fun onApiCallFailure(error: String) {
    }

    fun fetchUserFromApi(){
        userListApiCallInterface.requestUserListFromApi(this)
    }

    fun insertUser(userModel: UserModel){
        CoroutineScope(Dispatchers.IO).async {
            userDao.insert(userModel)
        }
    }

    fun delete(userModel: UserModel){
        userDao.delete(userModel)
    }

    fun getUserLists(): LiveData<List<UserModel>> {
        return userDao.getUserList()
    }

}