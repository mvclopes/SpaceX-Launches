package com.mvclopes.spacexlaunches.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.mvclopes.spacexlaunches.R
import com.mvclopes.spacexlaunches.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}
