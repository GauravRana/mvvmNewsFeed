package com.assignment.feeds.di

import com.assignment.feeds.data.remote.api.DataAPI
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(DataAPI::class.java) }
}