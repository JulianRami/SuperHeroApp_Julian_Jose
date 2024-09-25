package com.example.superheroapp.data.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.superheroapp.data.viewmodel.SuperherosViewModel
import com.example.superheroapp.databinding.ActivitySuperheroBinding
import kotlinx.coroutines.launch

class SuperherosActivity : AppCompatActivity() {

    //private lateinit var rvEpisodesAdapter: RVEpisodesAdapter
    private lateinit var binding: ActivitySuperheroBinding
    private val superheroViewModel: SuperherosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
//        getCharacterId()
    }

    private fun initRV() {
//        rvEpisodesAdapter = RVEpisodesAdapter()
//        binding.rvCharacterEpisodes.apply {
//            layoutManager = LinearLayoutManager(this@SuperherosActivity)
//            adapter = rvEpisodesAdapter
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            superheroViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.superheros?.let { superhero ->
//                        tvCharacterName.text = superhero.name
//                        tvCharacterSpecie.text = superhero.species
//                        tvCharacterGender.text = superhero.gender
//                        tvCharacterStatus.text = superhero.status
                        //character.image?.let { ivCharacterPhoto.loadImage(it) }
                    }
//                    if (uiState.episodes.isNotEmpty()) {
//                        rvEpisodesAdapter.episodes = uiState.episodes
//                        rvEpisodesAdapter.notifyDataSetChanged()
//                    }
                    pbCharacter.visibility = if (uiState.isLoading) View.VISIBLE else View.INVISIBLE
//                    pbEpisodes.visibility = if (uiState.isEpisodeListLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }
//
//    private fun getCharacterId() {
//        val characterId = intent.extras?.getInt(SUPERHERO_ID)
//        characterId?.let {
//            superheroViewModel.getSuperheroInfo(characterId)
//        }
//    }

    companion object {
        const val SUPERHERO_ID = "superheroId"
    }
}