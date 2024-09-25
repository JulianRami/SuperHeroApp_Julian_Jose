package com.example.superheroapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.superheroapp.ui.screens.enemies.Enemies.Companion.ID_HERO
import com.example.superheroapp.databinding.ActivityMainBinding
import com.example.superheroapp.ui.screens.enemies.Enemies
import com.example.superheroapp.ui.screens.friends.Friends

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        launchNewActivity(1)
    }

    private fun launchNewActivity(id: Int) {
        val intent = Intent(this, Enemies::class.java).apply {
            putExtras(bundleOf(ID_HERO to id))
        }
        startActivity(intent)
        finish()
    }
    /*private fun launchNewActivity(id: Int) {
        val intent = Intent(this, Friends::class.java).apply {
            putExtras(bundleOf(ID_HERO to id))
        }
        startActivity(intent)
        finish()
    }*/
}