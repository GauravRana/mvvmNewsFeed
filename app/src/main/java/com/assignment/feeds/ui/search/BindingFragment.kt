package com.assignment.feeds.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false)
        return binding.apply { binding = this }.root
    }

    @Suppress("DEPRECATION")
    public fun isNetworkConnected(context: Context):Boolean {
        try{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo!!.isConnected
        }catch (e:Exception){
            e.printStackTrace()
        }
        return false
    }
}