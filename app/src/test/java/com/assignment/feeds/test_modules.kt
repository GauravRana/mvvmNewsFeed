package com.assignment.feeds

import com.assignment.feeds.data.remote.api.DataAPI

import org.koin.dsl.module.module

val testApiModule = module {
    single { DataTestAPI() as DataAPI }
}