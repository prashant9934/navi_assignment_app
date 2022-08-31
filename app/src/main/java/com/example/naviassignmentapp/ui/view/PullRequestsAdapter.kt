package com.example.naviassignmentapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignmentapp.databinding.PullRequestItemViewBinding
import com.example.naviassignmentapp.extentions.show
import com.example.naviassignmentapp.ui.viewmodel.model.PullRequestModel

class PullRequestsAdapter(private val onClick: (pullRequest: PullRequestModel) -> Unit) :
    ListAdapter<PullRequestModel, PullRequestsAdapter.ViewHolder>(PullRequestDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            PullRequestItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: PullRequestItemViewBinding,
        private val onClick: (pullRequest: PullRequestModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
        }

        fun bind(item: PullRequestModel) {
            binding.title.text = item.title
            binding.closedDate.text = item.closedDate
            binding.createdDate.text = item.createdDate
            binding.userName.text = item.user.name
            binding.userImage.show(item.user.avatarUrl)
        }
    }
}

class PullRequestDiffUtilCallBack : DiffUtil.ItemCallback<PullRequestModel>() {
    override fun areItemsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
        if (oldItem.title != newItem.title) return false
        if (oldItem.closedDate != newItem.closedDate) return false
        if (oldItem.createdDate != newItem.createdDate) return false
        if (oldItem.user.name != newItem.user.name) return false
        return true
    }


}