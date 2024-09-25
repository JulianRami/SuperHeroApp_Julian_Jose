package com.example.superheroapp.ui.screens.friends

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.data.generateSuperheroes
import com.example.superheroapp.data.viewmodel.FriendViewModel
import com.example.superheroapp.databinding.ActivityFriendsBinding
import com.example.superheroapp.ui.screens.friends.rv.RVFriendAdapter
import kotlinx.coroutines.launch

class Friends : AppCompatActivity() {
    private lateinit var rvFriendsAdapter: RVFriendAdapter
    private lateinit var binding: ActivityFriendsBinding
    private val friendsList = generateSuperheroes()
    private val friendsViewModel: FriendViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
        getEnemyId()
    }

    private fun initRV() {
        rvFriendsAdapter = RVFriendAdapter()
        binding.rvFriends.adapter = rvFriendsAdapter
        binding.rvFriends.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            friendsViewModel.friend.collect { uiState ->
                with(binding) {
                    uiState.superhero?.let { hero ->
                        Log.e("---HeroI", "${hero.friends}")
                        val listFriends = friendsList.filter { enemy -> hero.friends.contains(enemy.id) }
                        Log.e("---frend", "$listFriends")

                        rvFriendsAdapter.friends =listFriends
                        rvFriendsAdapter.notifyDataSetChanged()
                    }
                    pbFriends.visibility =
                        if (uiState.isFriendLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun getEnemyId() {
        val idHero = intent.extras?.getInt(ID_HERO)
        idHero?.let {
            friendsViewModel.getHero(it)
        }
    }

    companion object {
        const val ID_HERO = "id"
    }
}