package com.example.kunalparte.sundaymobtask.Users.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.example.kunalparte.sundaymobtask.R
import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.Users.viewModel.UserViewModel
import com.example.kunalparte.sundaymobtask.utils.Consts
import com.example.kunalparte.sundaymobtask.utils.GlideLoader
import com.example.kunalparte.sundaymobtask.utils.ImageScaleListener
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(), View.OnTouchListener {

    lateinit var userModel: UserModel
    lateinit var userViewModel: UserViewModel
    lateinit var scaleGestureDetector: ScaleGestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getIntentData()
        supportActionBar?.setTitle(userModel.login)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        GlideLoader.url(this).load(userModel.avatar_url).into(userDetailImageView)
        scaleGestureDetector = ScaleGestureDetector(this,ImageScaleListener(userDetailImageView) )
        userDetailImageView.setOnTouchListener(this)

    }

    fun getIntentData() {
        userModel = intent.getParcelableExtra(Consts.EXTRA_USER_DATA)

    }

    fun deleteUser(){
        userViewModel.delete(userModel)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_user_list_menu, menu)
       return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.deleteButton -> deleteUser()
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun resScaleImageView(){
        userDetailImageView.scaleX = 1.5f
        userDetailImageView.scaleY = 1f
    }
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            scaleGestureDetector.onTouchEvent(event)
        when(event?.action){
            MotionEvent.ACTION_UP -> resScaleImageView()
        }
        return true
    }
}
