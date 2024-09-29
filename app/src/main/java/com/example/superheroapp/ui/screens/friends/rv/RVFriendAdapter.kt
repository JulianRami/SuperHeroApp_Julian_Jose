package com.example.superheroapp.ui.screens.friends.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.databinding.ActivityViewFriendsBinding

class RVFriendAdapter: RecyclerView.Adapter<FriendViewHolder>() {
    var friends: List<Superhero> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ActivityViewFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friends[position])
    }
}