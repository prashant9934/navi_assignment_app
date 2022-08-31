package com.example.naviassignmentapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignmentapp.databinding.PullRequestItemViewBinding
import com.example.naviassignmentapp.extentions.show
import com.example.naviassignmentapp.ui.viewmodel.PullRequestUiModel

class PullRequestsAdapter :
    PagingDataAdapter<PullRequestUiModel, PullRequestsAdapter.ViewHolder>(
        PullRequestDiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            PullRequestItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(
        private val binding: PullRequestItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PullRequestUiModel) {
            binding.title.text = item.title
            binding.closedDate.text = item.closedDate
            binding.createdDate.text = item.createdDate
            binding.userName.text = item.user.name
            binding.userImage.show(item.user.avatarUrl)
        }
    }
}

class PullRequestDiffUtilCallBack : DiffUtil.ItemCallback<PullRequestUiModel>() {
    override fun areItemsTheSame(
        oldItem: PullRequestUiModel,
        newItem: PullRequestUiModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PullRequestUiModel,
        newItem: PullRequestUiModel
    ): Boolean {
        if (oldItem.title != newItem.title) return false
        if (oldItem.closedDate != newItem.closedDate) return false
        if (oldItem.createdDate != newItem.createdDate) return false
        if (oldItem.user.name != newItem.user.name) return false
        return true
    }
}