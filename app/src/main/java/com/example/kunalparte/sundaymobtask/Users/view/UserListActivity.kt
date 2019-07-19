package com.example.kunalparte.sundaymobtask.Users.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.kunalparte.sundaymobtask.R
import com.example.kunalparte.sundaymobtask.Users.adapter.UserListAdapter
import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.Users.viewModel.UserViewModel
import com.example.kunalparte.sundaymobtask.utils.Consts
import com.example.kunalparte.sundaymobtask.utils.OnRecyclerViewItemClicked
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class UserListActivity : AppCompatActivity(), OnRecyclerViewItemClicked {

    var userList : List<UserModel> = emptyList()
    val userListAdapter : UserListAdapter by lazy {
        UserListAdapter(this, this)
    }
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userListView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = userListAdapter
        }
        insertUserFromApiCall()
        setDataOnRecyclerView()
        onAddUserClicked()
    }

    fun insertUserFromApiCall(){
        userViewModel.insertUsersFromApiCall()
    }

    private fun setDataOnRecyclerView(){
        userViewModel.getUserList().observe(this, Observer {
            userListAdapter.userList = it!!
            this.userList = userListAdapter.userList
        })
    }

    fun onAddUserClicked(){
        addActiviyButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        })
    }

    fun onRecyclerItemClicked( position : Int){
        val intent : Intent = Intent(this,UserDetailActivity::class.java)
        intent.putExtra(Consts.EXTRA_USER_DATA,userList.get(position))
        startActivity(intent)
    }

    override fun onItemClickListener(position: Int) {
        onRecyclerItemClicked(position)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
