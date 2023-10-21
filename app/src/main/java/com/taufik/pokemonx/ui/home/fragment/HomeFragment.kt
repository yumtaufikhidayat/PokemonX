package com.taufik.pokemonx.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.pokemonx.R
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.databinding.FragmentHomeBinding
import com.taufik.pokemonx.model.home.PokemonListResult
import com.taufik.pokemonx.ui.detail.fragment.DetailFragment
import com.taufik.pokemonx.ui.home.adapter.HomeAdapter
import com.taufik.pokemonx.ui.home.viewmodel.HomeViewModel
import com.taufik.pokemonx.utils.getPokemonImage
import com.taufik.pokemonx.utils.getPokemonNumber
import com.taufik.pokemonx.utils.showErrorLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy { HomeAdapter { navigateToDetail(it.name) } }

    private var doubleBackToExitPressedOnce = false
    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (doubleBackToExitPressedOnce) {
                requireActivity().finish()
                return
            }

            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), getString(R.string.text_back_to_exit), Toast.LENGTH_SHORT).show()

            lifecycleScope.launch {
                delay(2.seconds)
                doubleBackToExitPressedOnce = false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )

        setToolbarHome()
        setHomeAdapter()
        setPokemonListObserver()
        showSearchPokemonList()
    }

    private fun setToolbarHome() {
        binding.toolbarHome.tvToolbar.text = getString(R.string.app_name)
    }

    private fun setHomeAdapter() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    private fun setPokemonListObserver() {
        viewModel.apply {
            getPokemonList()
            getPokemonList.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> showLoading(true)
                    is NetworkResult.Success -> {
                        showLoading(false)
                        val results = it.data?.results
                        results?.let { listOfData ->
                            homeAdapter.setSearchData(listOfData)
                            listOfData.forEach { pokemonList ->
                                insertPokemonList(
                                    id = getPokemonNumber(pokemonList.url),
                                    name = pokemonList.name,
                                    url = pokemonList.url,
                                    imageUrl = getPokemonImage(pokemonList.url)
                                )
                            }
                        }
                        sortFilterData(it.data?.results)
                    }
                    is NetworkResult.Error -> {
                        showLoading(false)
                        showErrorLog(TAG, it.message)
                    }
                }
            }
        }
    }

    private fun showSearchPokemonList() {
        binding.etSearch.addTextChangedListener(afterTextChanged = { q ->
            homeAdapter.filter.filter(q.toString())
        })
    }

    private fun sortFilterData(results: List<PokemonListResult>?) {
        if (!results.isNullOrEmpty()) {
            binding.rgSortFilter.setOnCheckedChangeListener { group, _ ->
                val radioId = group.checkedRadioButtonId
                val selectedRadioButton = group.findViewById<RadioButton>(radioId)
                when (group.indexOfChild(selectedRadioButton)) {
                    0 -> homeAdapter.setSearchData(results)
                    1 -> homeAdapter.sortDataAscending(results.sortedBy { it.name })
                    else -> homeAdapter.sortDataDescending(results.sortedByDescending { it.name })
                }
            }
        } else {
            showErrorLog(TAG, getString(R.string.text_oops))
        }
    }

    private fun insertPokemonList(
        id: Int,
        name: String,
        url: String,
        imageUrl: String,
    ) {
        viewModel.savePokemonList(
            id = id,
            url = url,
            name = name,
            imageUrl = imageUrl
        )
    }

    private fun navigateToDetail(name: String) {
        val bundle = bundleOf(
            DetailFragment.EXTRA_DETAIL to name
        )
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.isVisible = isShow
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "home"
    }
}