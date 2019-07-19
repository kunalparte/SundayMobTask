package com.example.kunalparte.sundaymobtask.Users.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kunalparte.sundaymobtask.Users.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( userEntity : UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( userEntity : List<UserModel>)

    @Query("Select * from Users")
    fun getUserList(): LiveData<List<UserModel>>

    @Delete
    fun delete(userModel: UserModel)

}