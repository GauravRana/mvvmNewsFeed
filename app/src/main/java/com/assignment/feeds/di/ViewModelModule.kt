package com.assignment.feeds.di

import com.assignment.feeds.ui.search.FeedViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { FeedViewModel(get(), get()) }
}