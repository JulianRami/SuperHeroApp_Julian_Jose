package com.example.superheroapp.data.ui.screens.superheros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futuramaapi.ui.screens.characters.rv.RVSuperheroAdapter
import com.example.superheroapp.data.ui.screens.SuperherosActivity
import com.example.superheroapp.data.ui.screens.SuperherosActivity.Companion.SUPERHERO_ID
import com.example.superheroapp.data.viewmodel.SuperherosViewModel
import com.example.superheroapp.databinding.ActivitySuperherosBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

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
            onViewInfoClickListener = { characterId ->
                launchCharacterActivity(characterId)
            }
        )
        binding.rvSupeheros.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvSuperheroAdapter
        }
    }

    private fun launchCharacterActivity(characterId: Int) {
        startActivity(
            Intent(
                this,
                SuperherosActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        SUPERHERO_ID to characterId
                    )
                )
            }
        )
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
}