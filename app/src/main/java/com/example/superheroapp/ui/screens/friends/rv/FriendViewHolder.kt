package com.example.superheroapp.ui.screens.friends.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.databinding.ActivityViewFriendsBinding

class FriendViewHolder(
    private val binding: ActivityViewFriendsBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Superhero) {
        with(binding) {
            Log.e("---HeroIDfriends", "${friend}")
            tvNameFriend.text = friend.name
            tvAlterName.text = friend.alterName
            ivImageFriend.setImageResource(friend.photo)
        }
    }
}