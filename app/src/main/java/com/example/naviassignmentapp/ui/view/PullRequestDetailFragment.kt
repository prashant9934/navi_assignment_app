package com.example.naviassignmentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.naviassignmentapp.R
import com.example.naviassignmentapp.databinding.FragmentPullRequestDetailBinding
import com.example.naviassignmentapp.ui.viewmodel.PullRequestDetailViewModel

class PullRequestDetailFragment : Fragment() {

    private var _binding: FragmentPullRequestDetailBinding? = null

    private val viewModel: PullRequestDetailViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPullRequestDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}