package com.example.futuramaapi.ui.screens.characters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.databinding.SuperheroViewBinding

class RVSuperheroAdapter(
    private val onViewInfoClickListener: (id: Int) -> Unit
): RecyclerView.Adapter<SuperheroPostViewHolder>() {

    var superheros = emptyList<Superhero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroPostViewHolder {
        val binding = SuperheroViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SuperheroPostViewHolder(
            binding = binding,
            onViewInfoClickListener = onViewInfoClickListener
        )
    }

    override fun onBindViewHolder(holder: SuperheroPostViewHolder, position: Int) {
        holder.bind(superheros[position])
    }

    override fun getItemCount(): Int = superheros.size

}