package com.assignment.feeds.di

import com.assignment.feeds.data.remote.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<AppDatabase>().getRowDataDao() }
}