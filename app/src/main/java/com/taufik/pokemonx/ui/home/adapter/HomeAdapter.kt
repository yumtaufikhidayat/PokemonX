package com.taufik.pokemonx.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.R
import com.taufik.pokemonx.databinding.ItemPokeListBinding
import com.taufik.pokemonx.model.home.PokemonListResult
import com.taufik.pokemonx.utils.getPokemonImage
import com.taufik.pokemonx.utils.loadImage
import com.taufik.pokemonx.utils.replaceFirstChar
import java.util.Locale

class HomeAdapter(
    private val onItemClickListener: (PokemonListResult) -> Unit
) : ListAdapter<PokemonListResult, HomeAdapter.ViewHolder>(
    HOME_DIFF_CALLBACK
), Filterable {

    private var listOfPokemon = listOf<PokemonListResult>()
    private val searchFilter = object : Filter() {
        override fun performFiltering(p0: CharSequence): FilterResults {
            val filteredList = mutableListOf<PokemonListResult>()
            if (p0.isEmpty()) {
                filteredList.addAll(listOfPokemon)
            } else {
                val filterPattern = p0.toString().lowercase(Locale.ROOT).trim()
                listOfPokemon.forEach {
                    if (it.name.lowercase().contains(filterPattern)) filteredList.add(it)
                }
            }

            val result = FilterResults()
            result.values = filteredList
            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            submitList(p1?.values as MutableList<PokemonListResult>?)
        }
    }

    fun setSearchData(list: List<PokemonListResult>) {
        this.listOfPokemon = list
        submitList(list)
    }

    fun sortDataAscending(list: List<PokemonListResult>) {
        this.listOfPokemon = list.sortedBy { it.name }
        submitList(list)
    }

    fun sortDataDescending(list: List<PokemonListResult>) {
        this.listOfPokemon = list.sortedByDescending { it.name }
        submitList(list)
    }

    override fun getFilter(): Filter = searchFilter

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
                imgPokeCharacter.loadImage(url = getPokemonImage(data.url))
                cardBgMenu.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.primary_700))
                tvPokeName.text = data.name.replaceFirstChar()
                clPokeList.setOnClickListener {
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