package com.mvclopes.spacexlaunches.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mvclopes.spacexlaunches.databinding.FragmentDetailBinding
import com.mvclopes.spacexlaunches.utils.loadImageWithProgress

class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadImageWithProgress(binding.missionPatch, args.launch.links.missionPatch)
        with(binding) {
            missionName.text = args.launch.missionName
            launchYear.text = args.launch.launchYear
            launchSuccess.text = if(args.launch.launchSuccess) "Yes" else "No"
            rocketName.text = args.launch.rocket.rocketName
            details.text = args.launch.details.ifEmpty { "Has no details" }
        }
        return binding.root
    }
}
