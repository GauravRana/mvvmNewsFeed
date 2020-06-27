package com.assignment.feeds.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.assignment.feeds.R
import com.assignment.feeds.data.remote.domain.Rows


class RepositoryAdapter(var items: List<Rows> = arrayListOf(), val vm: FeedViewModel) :
    RecyclerView.Adapter<RepositoryAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.item_repository, parent, false)
        return BindingHolder(binding)
    }
    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(items, position)
    }
    override fun getItemCount() = items.size

    class BindingHolder(binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {
        val binding: ViewDataBinding = binding
        fun bind(items: List<Rows>, position: Int) {
            binding!!.setVariable(BR.item, items[position]);
            binding!!.executePendingBindings();
        }
    }
}