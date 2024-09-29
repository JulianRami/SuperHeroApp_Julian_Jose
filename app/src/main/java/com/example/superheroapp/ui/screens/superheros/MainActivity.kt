package com.example.superheroapp.ui.screens.superheros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.data.di.DataHelper
import com.example.superheroapp.ui.screens.superheros.rv.RVSuperheroAdapter
import com.example.superheroapp.data.viewmodel.SuperherosViewModel
import com.example.superheroapp.databinding.ActivitySuperherosBinding
import com.example.superheroapp.ui.screens.enemies.Enemies
import com.example.superheroapp.ui.screens.friends.Friends
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataHelper: DataHelper
    private val superherosViewModel: SuperherosViewModel by viewModels()
    private lateinit var binding: ActivitySuperherosBinding
    private lateinit var rvSuperheroAdapter: RVSuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperherosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
    }

    private fun initRV() {
        rvSuperheroAdapter = RVSuperheroAdapter(
            onViewInfoClickListener = { superheroId ->
                launchSuperheroFriendsActivity(superheroId)
            },
            onViewInfoTwoClickListener = { superheroId ->
                launchEnemiesActivity(superheroId)
            },
            dataHelper = dataHelper
        )

        binding.rvSupeheros.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvSuperheroAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            superherosViewModel.uiState.collect { uiState ->
                uiState.superheros?.let { superherosList ->
                    rvSuperheroAdapter.superheros = superherosList
                    rvSuperheroAdapter.notifyDataSetChanged()
                }
                binding.rvSupeheros.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbSuperheros.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }

    private fun launchSuperheroFriendsActivity(superheroId: Int) {
        startActivity(
            Intent(
                this,
                Friends::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        Friends.ID_HERO to superheroId
                    )
                )
            }
        )
    }

    private fun launchEnemiesActivity(superheroId: Int) {
        startActivity(
            Intent(
                this,
                Enemies::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        Enemies.ID_HERO to superheroId
                    )
                )
            }
        )
    }
}