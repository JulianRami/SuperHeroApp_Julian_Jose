package com.example.superheroapp.ui.screens.enemies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.data.generateEnemies
import com.example.superheroapp.data.viewmodel.EnemyViewModel
import com.example.superheroapp.databinding.ActivityEnemiesBinding
import com.example.superheroapp.ui.screens.enemies.rv.RVEnemyAdapter
import kotlinx.coroutines.launch
import android.view.View

class Enemies : AppCompatActivity() {
    private lateinit var rvEnemiesAdapter:  RVEnemyAdapter
    private lateinit var binding: ActivityEnemiesBinding
    private val enemiesViewModel: EnemyViewModel by viewModels()
    private val enemiesList = generateEnemies()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnemiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
        getEnemyId()
    }
    private fun initRV() {
        rvEnemiesAdapter = RVEnemyAdapter()
        binding.rvEnemies.adapter = rvEnemiesAdapter
        binding.rvEnemies.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            enemiesViewModel.enemy.collect { uiState ->
                with(binding) {
                    uiState.superhero?.let { hero ->
                        Log.e("---HeroIDenemies", "${hero.enemies}")

                        val listEnemies = enemiesList.filter { enemy -> hero.enemies.contains(enemy.id) }
                        Log.e("---enemies", "$listEnemies")
                        ivImageEnemy.setImageResource(hero.mainEnemy.photo)
                        tvEnemyName.text = hero.mainEnemy.name
                        rvEnemiesAdapter.enemies =listEnemies
                        rvEnemiesAdapter.notifyDataSetChanged()
                    }
                    pbEnemyMain.visibility =
                        if (uiState.isEnemyLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun getEnemyId() {
        val idHero = intent.extras?.getInt(ID_HERO)
        idHero?.let {
            enemiesViewModel.getHero(it)
        }
    }

    companion object {
        const val ID_HERO = "id"
    }
}