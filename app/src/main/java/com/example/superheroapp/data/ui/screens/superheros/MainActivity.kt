package com.example.superheroapp.data.ui.screens.superheros

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.data.ui.screens.superheros.rv.RVSuperheroAdapter
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
            onViewInfoClickListener = { superheroId ->
                launchSuperheroActivity(superheroId)
            }
        )
        binding.rvSupeheros.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvSuperheroAdapter
        }
    }

    private fun launchSuperheroActivity(superheroId: Int) {
//        startActivity(
//            Intent(
//                this,
//                SuperherosActivity::class.java
//            ).apply {
//                putExtras(
//                    bundleOf(
//                        SUPERHERO_ID to superheroId
//                    )
//                )
//            }
//        )
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