package com.example.kunalparte.sundaymobtask.Users.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunalparte.sundaymobtask.R
import com.example.kunalparte.sundaymobtask.Users.model.UserModel
import com.example.kunalparte.sundaymobtask.utils.GlideLoader
import com.example.kunalparte.sundaymobtask.utils.OnRecyclerViewItemClicked
import kotlinx.android.synthetic.main.list_item_layout.view.*
import kotlin.properties.Delegates

class UserListAdapter constructor(context: Context, onRecyclerViewItemClicked: OnRecyclerViewItemClicked) : RecyclerView.Adapter<UserListAdapter.VHolder>() {

    val onRecyclerViewItemClicked = onRecyclerViewItemClicked
    val context : Context = context
    var userList : List<UserModel> by Delegates.observable(emptyList()){ property, oldValue, newValue ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserListAdapter.VHolder {
        return VHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_layout,p0, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListAdapter.VHolder, position: Int) {
        holder.itemView.userNameTextView.setText(userList.get(position).login)
        GlideLoader.url(context).load(userList.get(position).avatar_url).into(holder.itemView.userImageView)
        holder.itemView.userTypeTextView.setText(userList.get(position).type)

        holder.itemView.setOnClickListener(View.OnClickListener {
            onRecyclerViewItemClicked.onItemClickListener(position)
        })
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}