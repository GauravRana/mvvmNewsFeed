package com.assignment.feeds.ui.search

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.feeds.R
import com.assignment.feeds.data.remote.db.entity.RowData
import com.assignment.feeds.data.remote.domain.Rows
import com.assignment.feeds.databinding.ActivityFeedsBinding
import com.assignment.feeds.ui.BindingActivity
import kotlinx.android.synthetic.main.activity_feeds.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FeedActivity : BindingActivity<ActivityFeedsBinding>()  {
    private var repositoryAdapter: RepositoryAdapter? = null

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_feeds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.setLifecycleOwner(this)
        val viewModel: FeedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        setAdapter()
        if (isNetworkConnected()) {
            tvNoInternet.visibility = View.GONE
            viewModel.refresh()
        }else {
            tvNoInternet.visibility = View.VISIBLE
        }
        viewModel.title.observe(this, Observer<String> {
            it -> supportActionBar!!.title = it
        })

        viewModel.items.observe(this, Observer<List<Rows>> {
                it -> if(it.isNotEmpty()) {
                tvNoInternet.visibility = View.GONE
                viewModel.deleteDB()
                for (element in it)
                    viewModel.saveToDB(element)
        }})

        viewModel.getAllPosts().observe(this, Observer<List<RowData>> {
                it -> tvNoInternet.visibility = View.GONE
                      repositoryAdapter?.setData(it)
        })
    }

    private fun setAdapter() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter()
        recyclerView!!.adapter = repositoryAdapter
    }

}