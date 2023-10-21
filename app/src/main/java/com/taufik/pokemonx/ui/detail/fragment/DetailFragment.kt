package com.taufik.pokemonx.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.pokemonx.R
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.databinding.FragmentDetailBinding
import com.taufik.pokemonx.ui.detail.adapter.AbilitiesAdapter
import com.taufik.pokemonx.ui.detail.adapter.StatsAdapter
import com.taufik.pokemonx.ui.detail.viewmodel.DetailViewModel
import com.taufik.pokemonx.utils.loadImage
import com.taufik.pokemonx.utils.replaceFirstChar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val abilitiesAdapter by lazy { AbilitiesAdapter() }
    private val statsAdapter by lazy { StatsAdapter() }

    private var name = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handlingBackArrow()
        getBundleData()
        initAdapter()
        showPokemonInfoObserver()
    }

    private fun handlingBackArrow() {
        binding.toolbarDetail.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getBundleData() {
        name = arguments?.getString(EXTRA_DETAIL).orEmpty()
    }

    private fun initAdapter() {
        initAbilitiesAdapter()
        initStatsAdapter()
    }

    private fun initAbilitiesAdapter() {
        binding.rvAbilities.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = abilitiesAdapter
        }
    }

    private fun initStatsAdapter() {
        binding.rvStats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = statsAdapter
        }
    }

    private fun showPokemonInfoObserver() {
        viewModel.apply {
            getPokemonByName(name)
            getPokemonByName.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        binding.apply {
                            it.data?.let { details ->
                                imgPokemon.loadImage(details.sprites.frontShiny)
                                tvPokemonName.text = getString(
                                    R.string.text_pokemon_name_rating,
                                    details.name.replaceFirstChar(),
                                    details.id.toString()
                                )
                                abilitiesAdapter.submitList(details.abilities)
                                statsAdapter.submitList(details.stats)
                            }
                        }
                    }

                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_DETAIL = "EXTRA_DETAIL"
    }
}