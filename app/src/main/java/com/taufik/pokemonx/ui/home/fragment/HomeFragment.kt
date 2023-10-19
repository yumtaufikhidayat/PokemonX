package com.taufik.pokemonx.ui.home.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.pokemonx.R
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.databinding.FragmentHomeBinding
import com.taufik.pokemonx.model.home.PokemonListResult
import com.taufik.pokemonx.ui.home.adapter.HomeAdapter
import com.taufik.pokemonx.ui.home.viewmodel.HomeViewModel
import com.taufik.pokemonx.utils.showErrorLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy { HomeAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        it.data?.results?.let { listOfData -> homeAdapter.setSearchData(listOfData) }
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
        binding.etSearch.apply {
            showKeyboard()
            setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard()
                    return@OnEditorActionListener true
                }
                false
            })

            addTextChangedListener(afterTextChanged = { q ->
                homeAdapter.filter.filter(q.toString())
            })
        }
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

    private fun showKeyboard() {
        binding.etSearch.apply {
            requestFocus()
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun hideKeyboard() {
        binding.etSearch.apply {
            clearFocus()
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.windowToken, 0)
        }
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