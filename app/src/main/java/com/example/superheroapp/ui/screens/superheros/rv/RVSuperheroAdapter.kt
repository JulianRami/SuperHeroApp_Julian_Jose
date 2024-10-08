package com.example.superheroapp.ui.screens.superheros.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.data.di.DataHelper
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.databinding.SuperheroViewBinding

class RVSuperheroAdapter(
    private val onViewInfoClickListener: (id: Int) -> Unit,
    private val onViewInfoTwoClickListener: (id: Int) -> Unit,
    private val dataHelper: DataHelper
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
            onViewInfoClickListener = onViewInfoClickListener,
            onViewInfoTwoClickListener = onViewInfoTwoClickListener,
            dataHelper = dataHelper
        )
    }

    override fun onBindViewHolder(holder: SuperheroPostViewHolder, position: Int) {
        holder.bind(superheros[position])
    }

    override fun getItemCount(): Int = superheros.size

}