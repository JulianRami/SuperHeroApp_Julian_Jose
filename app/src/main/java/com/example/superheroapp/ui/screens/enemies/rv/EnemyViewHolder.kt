package com.example.superheroapp.ui.screens.enemies.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.data.models.Enemy
import com.example.superheroapp.databinding.ActivityViewEnemiesBinding

class EnemyViewHolder(
    private val binding: ActivityViewEnemiesBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(enemy: Enemy) {
        with(binding) {
            tvNameEnemy.text = tvNameEnemy.context.getString(
                R.string.name_enemy, enemy.name)

            tvIdEnemy.text =  tvIdEnemy.context.getString(
                R.string.id_enemy, enemy.id.toString())

            ivImageEnemy.setImageResource(enemy.photo)
        }
    }
}