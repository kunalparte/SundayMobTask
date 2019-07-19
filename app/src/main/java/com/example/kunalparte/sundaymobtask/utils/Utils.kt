package com.example.kunalparte.sundaymobtask.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import java.util.ArrayList

class Utils {

    companion object{
        fun getPath(context: Context, uri: Uri): String {
            var result: String? = null
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(uri, proj, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    val column_index = cursor.getColumnIndexOrThrow(proj[0])
                    result = cursor.getString(column_index)
                }
                cursor.close()
            }
            if (result == null) {
                result = "Not found"
            }
            return result
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun checkPermission(activity: Activity): Boolean {
            val storagePermission = activity.checkSelfPermission(WRITE_EXTERNAL_STORAGE)
            val storageReadPermission = activity.checkSelfPermission(READ_EXTERNAL_STORAGE)

            val listPermissionsNeeded = ArrayList<String>()
            var granted : Boolean = false
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(WRITE_EXTERNAL_STORAGE)
                granted = false
            }else{
                granted = true
            }
            if (storageReadPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(READ_EXTERNAL_STORAGE)
                granted = false
            }else{
                granted = true
            }

            if (!listPermissionsNeeded.isEmpty()) {
                activity.requestPermissions(listPermissionsNeeded.toTypedArray(), 2)
                return false
            }
            return granted
        }
    }
}