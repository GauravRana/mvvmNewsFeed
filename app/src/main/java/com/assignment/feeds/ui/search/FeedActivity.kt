@file:Suppress("DEPRECATION")

package com.assignment.feeds.ui.search

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.feeds.R
import com.assignment.feeds.databinding.ActivityFeedsBinding
import com.assignment.feeds.ui.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


class FeedActivity : BindingActivity<ActivityFeedsBinding>()  {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_feeds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        val viewModel: FeedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.title.observe(this, Observer<String> {
                it -> supportActionBar!!.title = it
        })
        openFragment()
    }

    private fun openFragment(){
        val fragment: Fragment = FeedFragment.newInstance()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment).commit()
    }

}