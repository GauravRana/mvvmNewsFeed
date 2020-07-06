package com.assignment.feeds.ui.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.feeds.R
import com.assignment.feeds.data.remote.db.entity.RowData
import com.assignment.feeds.data.remote.domain.Rows
import com.assignment.feeds.databinding.FragmentFeedBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FeedFragment : BindingFragment<FragmentFeedBinding>() {
    private var repositoryAdapter: RepositoryAdapter? = null
    private var viewFragment : View ?= null
    private var recyclerView : RecyclerView ?= null
    private var tvNoInternet : TextView ? = null
    private var swipeRefresh : SwipeRefreshLayout ? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            FeedFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getLayoutResId() = R.layout.fragment_feed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vmf = getViewModel()
        binding.lifecycleOwner = this
        viewFragment = inflater.inflate(getLayoutResId(), container, false)
        initialize(viewFragment!!)
        val viewModel: FeedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        if (isNetworkConnected(activity!!)) {
            tvNoInternet!!.visibility = View.GONE
        }else {
            swipeRefresh!!.isRefreshing = false
            tvNoInternet!!.visibility = View.VISIBLE
        }

        swipeRefresh!!.setOnRefreshListener {
            swipeRefresh!!.isRefreshing = true
            viewModel.refresh()
            swipeRefreshOff()
        }

        viewModel.items.observe(activity!!, Observer<List<Rows>> {
                it -> if(it.isNotEmpty()) {
            swipeRefresh!!.isRefreshing = false
            tvNoInternet!!.visibility = View.GONE
            viewModel.deleteDB()
            for (element in it)
                viewModel.saveToDB(element)
        }})

        viewModel.getAllPosts().observe(activity!!, Observer<List<RowData>> {
                it -> tvNoInternet!!.visibility = View.GONE
            swipeRefresh!!.isRefreshing = false
            repositoryAdapter?.setData(it)
        })
        return viewFragment
    }

    private fun setAdapter() {
        recyclerView!!.layoutManager = LinearLayoutManager(activity!!)
        repositoryAdapter = RepositoryAdapter()
        recyclerView!!.adapter = repositoryAdapter
    }


    private fun initialize(rootView: View){
        recyclerView = rootView.findViewById(R.id.rVItems)
        tvNoInternet = rootView.findViewById(R.id.tvNoInternet)
        swipeRefresh = rootView.findViewById(R.id.swipeRefresh)
        setAdapter()
    }

    private fun swipeRefreshOff(){
        Handler().postDelayed({
            swipeRefresh!!.isRefreshing = false
        }, 3000)
    }
}