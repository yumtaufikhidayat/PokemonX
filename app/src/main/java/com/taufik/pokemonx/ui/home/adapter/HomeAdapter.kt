package com.taufik.pokemonx.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.databinding.ItemPokeListBinding
import com.taufik.pokemonx.model.home.PokemonListResult
import com.taufik.pokemonx.utils.getPokemonNumber
import com.taufik.pokemonx.utils.loadImage

class HomeAdapter(
//    private val imageUrl: String,
    private val onItemClickListener: (PokemonListResult) -> Unit
) : ListAdapter<PokemonListResult, HomeAdapter.ViewHolder>(
    HOME_DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPokeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PokemonListResult) {
            binding.apply {
                imgPokeCharacter.loadImage(
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonNumber(data.url)}.png"
                )
                tvPokeName.text = data.name
                itemView.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        private val HOME_DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonListResult>() {
            override fun areItemsTheSame(
                oldItem: PokemonListResult,
                newItem: PokemonListResult
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonListResult,
                newItem: PokemonListResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}