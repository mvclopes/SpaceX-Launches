package com.mvclopes.spacexlaunches.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.ListLaunchItemBinding
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.utils.loadImage

class LaunchAdapter(
    private val cardListener: () -> Unit,
    private val favoriteListener: () -> Unit
): ListAdapter<Launch, LaunchItemHolder>(LaunchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchItemHolder {
        return LaunchItemHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LaunchItemHolder, position: Int) {
        holder.bind(getItem(position), cardListener, favoriteListener)
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
    fun bind(launchItem: Launch, cardListener: () -> Unit, favoriteListener: () -> Unit){
        val context = binding.root.context
        binding.missionName.text = launchItem.missionName
        binding.launchYear.text =
            context.getString(R.string.launch_year, launchItem.launchYear)
        loadImage(binding.missionPatch, launchItem.links.missionPatch)
        binding.launchCard.setOnClickListener { cardListener() }
        binding.iconFavorite.setOnClickListener { favoriteListener() }
    }

    companion object{
        fun from(parent: ViewGroup): LaunchItemHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListLaunchItemBinding.inflate(inflater, parent, false)
            return LaunchItemHolder(binding)
        }
    }
}
