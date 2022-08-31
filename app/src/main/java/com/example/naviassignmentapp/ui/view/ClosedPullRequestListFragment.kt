package com.example.naviassignmentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.naviassignmentapp.R
import com.example.naviassignmentapp.databinding.FragmentClosedPullRequestListBinding
import com.example.naviassignmentapp.ui.viewmodel.ClosedPullRequestListViewModel
import com.example.naviassignmentapp.ui.viewmodel.ScreenState
import com.example.naviassignmentapp.ui.viewmodel.model.PullRequestModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClosedPullRequestListFragment : Fragment() {

    private var _binding: FragmentClosedPullRequestListBinding? = null

    private val viewModel by viewModels<ClosedPullRequestListViewModel>()

    private lateinit var adapter: PullRequestsAdapter

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
        viewModel.onCreate()
        initRv()
        initData()
    }

    private fun initRv() {
        adapter = PullRequestsAdapter {
            findNavController().navigate(R.id.action_ClosedPullRequestListFragment_to_PullRequestDetailFragment)
        }
        binding.pullRequestRv.adapter = adapter
    }

    private fun initData() {
        viewModel.pullRequests.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is ScreenState.Loading -> showLoadingView()
                is ScreenState.Success -> loadData(screenState.data)
                is ScreenState.Error -> showError(screenState.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        hideLoadingView()
    }

    private fun loadData(data: List<PullRequestModel>) {
        adapter.submitList(data)
        hideLoadingView()
    }

    private fun showLoadingView() {
        binding.loadingView.visibility = View.VISIBLE
    }

    private fun hideLoadingView() {
        binding.loadingView.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}