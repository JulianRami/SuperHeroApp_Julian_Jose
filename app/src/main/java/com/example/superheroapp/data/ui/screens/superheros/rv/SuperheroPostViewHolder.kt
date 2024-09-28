package com.example.superheroapp.data.ui.screens.superheros.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.data.generateLocations
import com.example.superheroapp.data.generatePowers
import com.example.superheroapp.data.models.Location
import com.example.superheroapp.data.models.Power
import com.example.superheroapp.data.models.Superhero
import com.example.superheroapp.databinding.SuperheroViewBinding

class SuperheroPostViewHolder(
    private val binding: SuperheroViewBinding,
    private val onViewInfoClickListener: (position: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    private val locationList = generateLocations()
    private val powerList = generatePowers()


    fun bind(superhero: Superhero) {
        with(binding) {
            btnFriends.setOnClickListener {
                onViewInfoClickListener(superhero.id)
            }

            tvSuperheroName.text = tvSuperheroName.context.getString(
                R.string.superhero_name_with_position,
                superhero.id,
                superhero.name
            )

            val locations = locationList.filter { location ->  superhero.locations.contains(location.id)}
            val powers = powerList.filter { power ->  superhero.powers.contains(power.id)}


            tvSuperheroAlterName.text =
                tvSuperheroAlterName.context.getString(R.string.superhero_alter_name, superhero.alterName)

            superhero.photo?.let { ivSuperheroPicture.setImageResource(it) }

            tvSuperheroMainEnemie.text =
                tvSuperheroMainEnemie.context.getString(R.string.superhero_enemy, superhero.mainEnemy.name)

            tvSuperheroLocations.text =
                tvSuperheroLocations.context.getString(R.string.superhero_locations, addTextLocations(locations))

            tvSuperheroPowers.text =
                tvSuperheroPowers.context.getString(R.string.superhero_powers, addTextPowers(powers))

        }
    }

    fun addTextPowers(powers: List<Power>): String {
        return powers.joinToString(", ") { it.name }
    }

    fun addTextLocations(locations: List<Location>): String {
        return locations.joinToString(", ") { it.name }
    }

}