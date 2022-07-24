package com.mvclopes.spacexlaunches.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.FragmentHomeBinding
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.presentation.adapter.LaunchAdapter
import com.mvclopes.spacexlaunches.presentation.state.HomeUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter: LaunchAdapter by lazy { LaunchAdapter() }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getAllLaunches()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeUiState.Loading -> showLoading()
                is HomeUiState.Success -> showContent(state.launches)
                is HomeUiState.Error -> showError()
            }
        }
        return binding.root
    }



    private fun showError() {
        with(binding) {
            errorContent.isVisible = true
            contentGroup.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showLoading() {
        with(binding) {
            contentGroup.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showContent(launches: List<Launch>) {
        binding.contentGroup.isVisible = true
        binding.progressBar.isVisible = false
        binding.launchesList.adapter = adapter
        adapter.submitList(launches)
    }

}
