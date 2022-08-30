package com.example.naviassignmentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.naviassignmentapp.R
import com.example.naviassignmentapp.databinding.FragmentClosedPullRequestListBinding
import com.example.naviassignmentapp.ui.viewmodel.ClosedPullRequestListViewModel

class ClosedPullRequestListFragment : Fragment() {

    private var _binding: FragmentClosedPullRequestListBinding? = null

    private val viewModel: ClosedPullRequestListViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentClosedPullRequestListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_ClosedPullRequestListFragment_to_PullRequestDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}