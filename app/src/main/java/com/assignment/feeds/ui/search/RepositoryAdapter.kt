package com.assignment.feeds.ui.search

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.assignment.feeds.R
import com.assignment.feeds.data.remote.db.entity.RowData
import com.assignment.feeds.data.remote.domain.Rows
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_repository.view.*


class RepositoryAdapter() : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var items: List<RowData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items[position].title.isNullOrEmpty()){
            holder.tvTitle.text = holder.itemView.context.getString(R.string.no_data)
        }else{
            holder.tvTitle.text = items[position].title
        }

        if(items[position].description.isNullOrEmpty()){
            holder.tvDesc.text = holder.itemView.context.getString(R.string.no_data)
        }else{
            holder.tvDesc.text = items[position].description
        }

        if (!items[position].imageHref.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(items[position].imageHref)
                .apply(RequestOptions().error(R.drawable.place))
                .override(100, 100)
                .into(holder.image)
        }else{
            Glide.with(holder.itemView.context)
                .load("")
                .apply(RequestOptions().error(R.drawable.place))
                .override(100, 100)
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int {
            return items.size
    }

    public class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvDesc: TextView = view.tvDesc
        val image: ImageView = view.image
    }

    fun setData(newData: List<RowData>) {
        if (newData.isNotEmpty()) {
            items = newData
            notifyDataSetChanged()
        }
    }

}