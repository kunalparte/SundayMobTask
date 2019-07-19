package com.example.kunalparte.sundaymobtask.Users.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "Users")
data class UserModel constructor(var login : String) : Parcelable{

    @PrimaryKey
    var id : String = ""
    var type : String = ""
    var avatar_url : String = ""

    constructor(parcel: Parcel) : this(parcel.readString()) {
        id = parcel.readString()
        type = parcel.readString()
        avatar_url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeString(avatar_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }


}