package com.example.superheroapp.ui.screens.enemies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.data.viewmodel.EnemyViewModel
import com.example.superheroapp.databinding.ActivityEnemiesBinding
import com.example.superheroapp.ui.screens.enemies.rv.RVEnemyAdapter
import kotlinx.coroutines.launch
import android.view.View
import com.example.superheroapp.data.di.DataHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class Enemies : AppCompatActivity() {
    @Inject
    lateinit var dataHelper: DataHelper
    private lateinit var rvEnemiesAdapter:  RVEnemyAdapter
    private lateinit var binding: ActivityEnemiesBinding
    private val enemiesViewModel: EnemyViewModel by viewModels()
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

                        val listEnemies = dataHelper.generateEnemies().filter { enemy -> hero.enemies.contains(enemy.id) }
                        Log.e("---enemies", "$listEnemies")
                        ivImageEnemy.setImageResource(hero.mainEnemy.photo)
                        tvEnemyName.text = hero.mainEnemy.name
                        rvEnemiesAdapter.enemies =listEnemies
                        rvEnemiesAdapter.notifyDataSetChanged()
                    }
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