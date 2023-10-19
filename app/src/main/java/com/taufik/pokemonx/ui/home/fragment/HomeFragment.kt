package com.taufik.pokemonx.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.pokemonx.R
import com.taufik.pokemonx.data.remote.NetworkResult
import com.taufik.pokemonx.databinding.FragmentHomeBinding
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
    }

    private fun setToolbarHome() {
        binding.toolbarHome.tvToolbar.text = getString(R.string.app_name)
    }

    private fun setHomeAdapter() {
        /*homeAdapter = HomeAdapter {
            Toast.makeText(requireContext(), "tes", Toast.LENGTH_SHORT).show()
        }*/
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
                        homeAdapter.submitList(it.data?.results)
                    }
                    is NetworkResult.Error -> {
                        showLoading(false)
                        showErrorLog(TAG, it.message)
                    }
                }
            }
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