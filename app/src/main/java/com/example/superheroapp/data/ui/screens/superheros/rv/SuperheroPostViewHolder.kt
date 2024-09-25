package com.example.futuramaapi.ui.screens.characters.rv

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.data.utils.loadCircleImage
import com.example.superheroapp.databinding.SuperheroViewBinding

class SuperheroPostViewHolder(
    private val binding: SuperheroViewBinding,
    private val onViewInfoClickListener: (position: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(superhero: Superhero) {
        with(binding) {
            btnEpisodes.setOnClickListener {
                onViewInfoClickListener(superhero.id)
            }

            tvCharacterName.text = tvCharacterName.context.getString(
                R.string.character_name_with_position,
                superhero.id,
                superhero.name
            )

            tvCharacterGender.text =
                tvCharacterGender.context.getString(R.string.character_gender, superhero.name)

            //superhero.photo?.let { ivCharacterPicture.loadCircleImage(it) }

        }
    }
}