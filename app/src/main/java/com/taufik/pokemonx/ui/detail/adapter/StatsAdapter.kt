package com.taufik.pokemonx.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.pokemonx.databinding.ItemStatsBinding
import com.taufik.pokemonx.model.detail.Stat
import com.taufik.pokemonx.utils.replaceFirstChar

class StatsAdapter: ListAdapter<Stat, StatsAdapter.ViewHolder>(STATS_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStatsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Stat) {
            binding.apply {
                tvStatsName.text = data.stat.name.replaceFirstChar()
                tvStatsPercent.text = data.baseStat.toString()
                with(pbStats) {
                    progress = data.baseStat
                    max = 110
                }
            }
        }
    }

    companion object {
        private val STATS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Stat>() {
            override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean {
                return oldItem.stat.url == newItem.stat.url
            }

            override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean {
                return oldItem == newItem
            }
        }
    }
}