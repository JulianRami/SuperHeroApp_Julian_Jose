package com.example.superheroapp.ui.screens.enemies.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.models.Enemy
import com.example.superheroapp.databinding.ActivityViewEnemiesBinding

class RVEnemyAdapter: RecyclerView.Adapter<EnemyViewHolder>() {
    var enemies: List<Enemy> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnemyViewHolder {
        val binding = ActivityViewEnemiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EnemyViewHolder(binding)
    }

    override fun getItemCount(): Int = enemies.size

    override fun onBindViewHolder(holder: EnemyViewHolder, position: Int) {
      holder.bind(enemies[position])
    }
}