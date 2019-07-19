package com.example.kunalparte.sundaymobtask.Users.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.kunalparte.sundaymobtask.R
import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.Users.viewModel.UserViewModel
import com.example.kunalparte.sundaymobtask.utils.Consts
import com.example.kunalparte.sundaymobtask.utils.GlideLoader
import com.example.kunalparte.sundaymobtask.utils.Utils
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var filePath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        onAddClicked()
    }


    fun onAddClicked(){
        addButton.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(userNameEditText.text)) {
                if (!TextUtils.isEmpty(userTypeEditText.text)){
                val userModel: UserModel = UserModel(userNameEditText.text.toString())
                userModel.type = userTypeEditText.text.toString()
                    if(!TextUtils.isEmpty(filePath)){
                        userModel.avatar_url = filePath
                    }
                userViewModel.insert(userModel)
                finish()
                }else{
                    userTypeEditText.setError("Field is mandatory")
                }
            }else{
                userNameEditText.setError("Field is mandatory")
            }
        })
    }

    fun openImageGallery() {
        if(Utils.checkPermission(this)) {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, Consts.EXTRA_GALLEY_REQUEST)//one can be replaced with any action code
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Consts.EXTRA_GALLEY_REQUEST) {
            if (resultCode == -1) {
                val selectedImage = data!!.data
                filePath = Utils.getPath(this, selectedImage)
                GlideLoader.url(this).load(filePath).into(addUserImageView)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_add_user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                android.R.id.home -> finish()
                R.id.addImageButton -> openImageGallery()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 2) {
            var granted = false
            if (grantResults.size > 1) {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        granted = true
                    } else {
                        granted = false
                        break
                    }
                }
                if (granted)
                    openImageGallery()
            }
        }
    }

}
