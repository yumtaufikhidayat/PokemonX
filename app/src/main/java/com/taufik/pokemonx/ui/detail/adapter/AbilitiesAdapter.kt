package com.taufik.pokemonx.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.databinding.ItemAbilitiesBinding
import com.taufik.pokemonx.model.detail.Ability
import com.taufik.pokemonx.utils.replaceFirstChar

class AbilitiesAdapter: ListAdapter<Ability, AbilitiesAdapter.ViewHolder>(ABILITIES_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAbilitiesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemAbilitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Ability) {
            binding.tvAbilities.text = data.ability.name.replaceFirstChar()
        }
    }

    companion object {
        private val ABILITIES_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ability>() {
            override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem.ability.url == newItem.ability.url
            }

            override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem == newItem
            }
        }
    }
}