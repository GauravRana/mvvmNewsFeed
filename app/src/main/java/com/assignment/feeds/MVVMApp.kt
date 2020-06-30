package com.assignment.feeds

import android.app.Application
import android.os.StrictMode
import com.assignment.feeds.di.apiModule
import com.assignment.feeds.di.networkModule
import com.assignment.feeds.di.roomModule
import com.assignment.feeds.di.viewModelModule
import org.koin.android.ext.android.startKoin



@Suppress("Unused")
class MVVMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

        startKoin(this, listOf(
            networkModule,
            apiModule,
            roomModule,
            viewModelModule
        ))
    }

}