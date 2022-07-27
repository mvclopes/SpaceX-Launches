package com.mvclopes.spacexlaunches.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.FragmentDetailBinding
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.utils.getFormattedString
import com.mvclopes.spacexlaunches.utils.loadImageWithProgress
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()
    private val launch: Launch by lazy { args.launch }
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.isFavoriteLaunch(launch)
        renderContent()
        viewModel.state.observe(viewLifecycleOwner) { state -> setFavoriteIcon(state) }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    private fun renderContent() {
        loadImageWithProgress(binding.missionPatch, launch.links.missionPatch)
        configureButtonOpenVideo()
        with(binding) {
            missionName.text = getFormattedString(R.string.mission_name_label, launch.missionName)
            launchYear.text = getFormattedString(R.string.launch_year_label, launch.launchYear)
            launchSuccess.text = getFormattedString(R.string.launch_success_label, setLaunchSuccessMessage())
            rocketName.text = getFormattedString(R.string.rocket_name_label, launch.rocket.rocketName)
            details.text = setDetailsMessage()
            favoriteIcon.setOnClickListener { viewModel.onFavoriteIconClicked(launch) }
        }
    }

    private fun setDetailsMessage() = launch.details.ifEmpty { "Has no details" }

    private fun setLaunchSuccessMessage() = if (launch.launchSuccess) "Yes" else "No"

    private fun setFavoriteIcon(state: DetailUiState?) {
        when (state?.isFavorite) {
            true -> binding.favoriteIcon.setImageDrawable(
                    AppCompatResources
                        .getDrawable(requireContext(), R.drawable.ic_favorite_full)
                )
            else -> binding.favoriteIcon.setImageDrawable(
                    AppCompatResources
                        .getDrawable(requireContext(), R.drawable.ic_favorite_border)
                )
        }
    }

    private fun configureButtonOpenVideo() {
        binding.buttonOpenVideo.isVisible = launch.links.video.isNotBlank()
        binding.buttonOpenVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launch.links.video))
            startActivity(intent)
        }
    }
}
