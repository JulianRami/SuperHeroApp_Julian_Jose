package com.example.superheroapp.ui.screens.enemies.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.models.Enemy
import com.example.superheroapp.databinding.ActivityViewEnemiesBinding

class EnemyViewHolder(
    private val binding: ActivityViewEnemiesBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(enemy: Enemy) {
        with(binding) {
            tvNameEnemy.text = enemy.name
            tvIdEnemy.text = enemy.id.toString()
            ivImageEnemy.setImageResource(enemy.photo)
        }
    }
}