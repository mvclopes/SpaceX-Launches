package com.mvclopes.spacexlaunches.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.FragmentHomeBinding
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.presentation.home.adapter.LaunchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter: LaunchAdapter by lazy { LaunchAdapter() }
    private val viewModel: HomeViewModel by viewModel()
    private val actionBar: ActionBar? by lazy {
        (requireActivity() as AppCompatActivity?)?.supportActionBar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getAllLaunches()
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeUiState.Loading -> showLoading()
                is HomeUiState.Success -> showContent(state.launches)
                is HomeUiState.Error -> showError(state.error)
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteItem -> {
                setActionBarTitle("Favorite launches")
                viewModel.getFavoriteLaunches()
            }
            R.id.lastYearLaunchesItem -> {
                setActionBarTitle("Last year launches")
                viewModel.getLastYearLaunches()
            }
            R.id.launchSuccessItem -> {
                setActionBarTitle("Only successful launches")
                viewModel.getOnlyLaunchSuccess()
            }
            R.id.allLaunchesItem -> {
                setActionBarTitle("All launches")
                viewModel.getAllLaunches()
            }
        }
        return true
    }

    private fun setActionBarTitle(title: String) {
        actionBar?.title = title
    }

    private fun showError(error: String?) {
        with(binding) {
            error?.let { errorLabel.text = getString(R.string.error_message, it) }
            errorContent.isVisible = true
            contentGroup.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun showLoading() {
        with(binding) {
            contentGroup.isVisible = false
            progressBar.isVisible = true
            errorContent.isVisible = false
        }
    }

    private fun showContent(launches: List<Launch>) {
        binding.launchesList.adapter = adapter
        adapter.submitList(launches)
        binding.contentGroup.isVisible = true
        binding.progressBar.isVisible = false
        binding.errorContent.isVisible = false
    }

}
