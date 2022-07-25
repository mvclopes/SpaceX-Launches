package com.mvclopes.spacexlaunches.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.ListLaunchItemBinding
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.presentation.home.HomeFragmentDirections
import com.mvclopes.spacexlaunches.utils.loadImage

class LaunchItemViewHolder private constructor(private val binding: ListLaunchItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(launchItem: Launch){
        val context = binding.root.context
        binding.missionName.text = launchItem.missionName
        binding.launchYear.text =
            context.getString(R.string.launch_year, launchItem.launchYear)
        loadImage(binding.missionPatch, launchItem.links.missionPatchSmall)
        binding.launchCard.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.navigateToDetail(launchItem))
        }
    }

    companion object{
        fun from(parent: ViewGroup): LaunchItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListLaunchItemBinding.inflate(inflater, parent, false)
            return LaunchItemViewHolder(binding)
        }
    }
}
