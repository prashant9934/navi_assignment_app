package com.example.naviassignmentapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.naviassignmentapp.R
import com.example.naviassignmentapp.databinding.FragmentClosedPullRequestListBinding
import com.example.naviassignmentapp.ui.viewmodel.ClosedPullRequestListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        initRv()
        initData()
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh()
    }

    private fun initRv() {
        adapter = PullRequestsAdapter()
        binding.pullRequestRv.adapter = adapter
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.pullRequests.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> showLoadingView()
                    is LoadState.Error -> showError((loadState.refresh as LoadState.Error).error.message?:"")
                    else -> hideLoadingView()
                }
                when(loadState.append) {
                    is LoadState.Loading -> {
                        showItemLoadingView()
                    }
                    is LoadState.Error -> {
                        showError((loadState.append as LoadState.Error).error.message?:"")
                    }
                    else -> {
                        hideItemLoadingView()
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        val bundle = Bundle()
        bundle.putString(ERROR_MESSAGE, message)
        findNavController().navigate(R.id.action_ClosedPullRequestListFragment_to_ErrorViewFragment, bundle)
        hideItemLoadingView()
        hideLoadingView()
    }

    private fun showLoadingView() {
        binding.loadingView.visibility = View.VISIBLE
    }

    private fun showItemLoadingView() {
        binding.loadingMoreItemView.visibility = View.VISIBLE
    }

    private fun hideItemLoadingView() {
        binding.loadingMoreItemView.visibility = View.GONE
    }

    private fun hideLoadingView() {
        binding.loadingView.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}