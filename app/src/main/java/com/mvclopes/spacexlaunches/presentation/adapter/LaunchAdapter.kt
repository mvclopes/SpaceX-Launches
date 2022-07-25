package com.mvclopes.spacexlaunches.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mvclopes.spacexlaunches.domain.model.Launch

class LaunchAdapter: ListAdapter<Launch, LaunchItemViewHolder>(LaunchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchItemViewHolder {
        return LaunchItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LaunchItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
