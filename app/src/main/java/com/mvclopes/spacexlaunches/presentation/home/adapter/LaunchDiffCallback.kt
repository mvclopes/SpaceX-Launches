package com.mvclopes.spacexlaunches.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mvclopes.spacexlaunches.domain.model.Launch

object LaunchDiffCallback: DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.flightNumber == newItem.flightNumber
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}
