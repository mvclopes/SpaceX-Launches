package com.mvclopes.spacexlaunches.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.FragmentDetailBinding
import com.mvclopes.spacexlaunches.utils.loadImageWithProgress
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.isFavoriteLaunch(args.launch)
        renderContent()
        viewModel.state.observe(viewLifecycleOwner) { state -> setFavoriteIcon(state) }
        return binding.root
    }

    private fun renderContent() {
        loadImageWithProgress(binding.missionPatch, args.launch.links.missionPatch)
        with(binding) {
            missionName.text = args.launch.missionName
            launchYear.text = args.launch.launchYear
            launchSuccess.text = setLaunchSuccessMessage()
            rocketName.text = args.launch.rocket.rocketName
            details.text = setDetailsMessage()
            favoriteIcon.setOnClickListener {
                viewModel.onFavoriteIconClicked(args.launch)
            }
        }
    }

    private fun setDetailsMessage() = args.launch.details.ifEmpty { "Has no details" }

    private fun setLaunchSuccessMessage() = if (args.launch.launchSuccess) "Yes" else "No"

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
//        when (state) {
//            DetailUiState.Success -> {
//                binding.favoriteIcon.setImageDrawable(
//                    AppCompatResources
//                        .getDrawable(requireContext(), R.drawable.ic_favorite_full)
//                )
//            }
//            else ->
//                binding.favoriteIcon.setImageDrawable(
//                    AppCompatResources
//                        .getDrawable(requireContext(), R.drawable.ic_favorite_border)
//                )
//        }
    }
}
