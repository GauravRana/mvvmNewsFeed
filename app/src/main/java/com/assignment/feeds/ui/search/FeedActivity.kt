package com.assignment.feeds.ui.search

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assignment.feeds.R
import com.assignment.feeds.databinding.ActivitySearchBinding
import com.assignment.feeds.ui.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


class FeedActivity : BindingActivity<ActivitySearchBinding>()  {


    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.setLifecycleOwner(this)
        val viewModel: FeedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refresh()
        viewModel.title.observe(this, Observer<String> {
            it -> supportActionBar!!.title = it
        })

    }




}