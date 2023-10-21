package com.taufik.pokemonx.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taufik.pokemonx.R
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.databinding.FragmentDetailBinding
import com.taufik.pokemonx.ui.detail.adapter.MovesAdapter
import com.taufik.pokemonx.ui.detail.adapter.StatsAdapter
import com.taufik.pokemonx.ui.detail.adapter.TypesAdapter
import com.taufik.pokemonx.ui.detail.viewmodel.DetailViewModel
import com.taufik.pokemonx.utils.loadImage
import com.taufik.pokemonx.utils.replaceFirstChar
import com.taufik.pokemonx.utils.showErrorLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val typesAdapter by lazy { TypesAdapter() }
    private val statsAdapter by lazy { StatsAdapter() }
    private val movesAdapter by lazy { MovesAdapter() }

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
        showPokemonInfoSpeciesObserver()
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
        initMovesAdapter()
    }

    private fun initAbilitiesAdapter() {
        binding.rvTypes.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = typesAdapter
        }
    }

    private fun initStatsAdapter() {
        binding.rvStats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
            adapter = statsAdapter
        }
    }

    private fun initMovesAdapter() {
        val flexLayoutManager = FlexboxLayoutManager(requireContext())
        flexLayoutManager.apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
        binding.rvMoves.apply {
            layoutManager = flexLayoutManager
            isNestedScrollingEnabled = false
            adapter = movesAdapter
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
                                imgPokemon.loadImage(details.sprites.frontDefault)
                                tvPokemonName.text = getString(
                                    R.string.text_pokemon_name_rating,
                                    details.name.replaceFirstChar(),
                                    details.id.toString()
                                )

                                val height = details.height.toDouble() / 10 // from dm -> m
                                val weight = details.weight / 10 // from hg -> kg
                                val heightStr = getString(R.string.text_height, height.toString())
                                val weightStr = getString(R.string.text_weight, weight.toString())
                                tvHeightWeight.text = getString(R.string.text_height_weight, heightStr, weightStr)

                                typesAdapter.submitList(details.types)
                                tvAbilitiesDesc.text = details.abilities.joinToString(", ") { ability ->
                                    ability.ability.name.replaceFirstChar()
                                }

                                statsAdapter.submitList(details.stats)
                                movesAdapter.submitList(details.moves)
                            }
                        }
                    }
                    is NetworkResult.Error -> showErrorLog(TAG, it.message)
                }
            }
        }
    }

    private fun showPokemonInfoSpeciesObserver() {
        viewModel.apply {
            getPokemonSpecies(name)
            getPokemonSpecies.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        binding.apply {
                            it.data?.let { species ->
                                val text = species.flavorTextEntries
                                    .filter { lang -> lang.language.name == "en" }
                                    .map { flavor ->
                                        flavor.flavorText
                                            .replace("\n", " ")
                                            .replace("\\f", " ")
                                    }
                                tvAboutPokemonDesc.text = text.toString()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .replace(".,", "")

                                tvGenerationHabitat.text = getString(
                                    R.string.text_generation_growth_rate_habitat,
                                    species.generation.name,
                                    species.growthRate.name,
                                    species.habitat.name
                                )
                            }
                        }
                    }
                    is NetworkResult.Error -> showErrorLog(TAG, it.message)
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
        private const val TAG = "detail"
    }
}