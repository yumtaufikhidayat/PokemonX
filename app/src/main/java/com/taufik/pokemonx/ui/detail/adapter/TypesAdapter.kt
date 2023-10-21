package com.taufik.pokemonx.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.databinding.ItemTypesBinding
import com.taufik.pokemonx.model.detail.Type
import com.taufik.pokemonx.utils.replaceFirstChar

class TypesAdapter: ListAdapter<Type, TypesAdapter.ViewHolder>(ABILITIES_DIFF_CALLBACK) {

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
        fun bind(data: Type) {
            binding.tvAbilities.text = data.type.name.replaceFirstChar()
        }
    }

    companion object {
        private val ABILITIES_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Type>() {
            override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean {
                return oldItem.type.url == newItem.type.url
            }

            override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean {
                return oldItem == newItem
            }
        }
    }
}