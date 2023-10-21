package com.taufik.pokemonx.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.databinding.ItemTypesBinding
import com.taufik.pokemonx.model.detail.Move
import com.taufik.pokemonx.utils.replaceFirstChar

class MovesAdapter : ListAdapter<Move, MovesAdapter.ViewHolder>(MOVES_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTypesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTypesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Move) {
            binding.tvAbilities.text = data.move.name.replaceFirstChar()
        }
    }

    companion object {
        private val MOVES_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Move>() {
            override fun areItemsTheSame(oldItem: Move, newItem: Move): Boolean {
                return oldItem.move.url == newItem.move.url
            }

            override fun areContentsTheSame(oldItem: Move, newItem: Move): Boolean {
                return oldItem == newItem
            }
        }
    }
}