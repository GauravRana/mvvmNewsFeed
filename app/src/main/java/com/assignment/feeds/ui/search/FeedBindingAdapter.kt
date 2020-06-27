package com.assignment.feeds.ui.search
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.assignment.feeds.R
import com.assignment.feeds.data.remote.domain.Rows

@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(view: RecyclerView, items: List<Rows>, vm: FeedViewModel) {
    view.adapter?.run {
        if (this is RepositoryAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        RepositoryAdapter(items, vm).apply { view.adapter = this }
    }
}


@BindingAdapter(value = ["imageUrl"])
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .apply(RequestOptions().error(R.drawable.place))
            .override(100, 100)
            .into(view)
    }else{
        Glide.with(view.context)
            .load("")
            .apply(RequestOptions().error(R.drawable.place))
            .override(100, 100)
            .into(view)
    }
}






