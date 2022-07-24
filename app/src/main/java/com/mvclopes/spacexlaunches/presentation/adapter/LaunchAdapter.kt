package com.mvclopes.spacexlaunches.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.ListLaunchItemBinding
import com.mvclopes.spacexlaunches.domain.model.Launch

class LaunchAdapter: ListAdapter<Launch, LaunchItemHolder>(LaunchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchItemHolder {
        return LaunchItemHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LaunchItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object LaunchDiffCallback: DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.flightNumber == newItem.flightNumber
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}

class LaunchItemHolder private constructor(private val binding: ListLaunchItemBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(launchItem: Launch){
        val context = binding.root.context
        binding.missionName.text = launchItem.missionName
        binding.launchYear.text =
            context.getString(R.string.launch_year, launchItem.launchYear)

        Glide
            .with(binding.missionPatch)
            .load(launchItem.links.missionPatch)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_broken_image)
            .into(binding.missionPatch)

    }

    companion object{
        fun from(parent: ViewGroup): LaunchItemHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListLaunchItemBinding.inflate(inflater, parent, false)
            return LaunchItemHolder(binding)
        }
    }
}
