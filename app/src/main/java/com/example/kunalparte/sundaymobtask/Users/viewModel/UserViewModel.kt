package com.example.kunalparte.sundaymobtask.Users.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kunalparte.sundaymobtask.Users.api.UserRepository
import com.example.kunalparte.sundaymobtask.Users.db.AppDatabase
import com.example.kunalparte.sundaymobtask.Users.model.UserModel

class UserViewModel(application : Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getAppDatabase(application)!!.userDao()
    val userRepo : UserRepository by lazy {
        UserRepository(userDao)
    }

    fun getUserList(): LiveData<List<UserModel>> {
        return userRepo.getUserLists()
    }

    fun insertUsersFromApiCall(){
        userRepo.fetchUserFromApi()
    }

    fun insert( userModel : UserModel){
        userRepo.insertUser(userModel)
    }

    fun delete(userModel: UserModel){
        userRepo.delete(userModel)
    }
}