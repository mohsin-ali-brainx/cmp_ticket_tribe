package com.brainx.cmp_base

import android.app.Application
import com.brainx.cmp_base.di.initKoin

import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}