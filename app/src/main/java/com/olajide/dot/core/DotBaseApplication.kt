package com.olajide.dot.core

import android.app.Application
import com.olajide.dot.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Application class to keep the global state of our app.
@HiltAndroidApp
class DotBaseApplication: Application(){
        override fun onCreate() {
            super.onCreate()

            if(BuildConfig.DEBUG){
                Timber.plant(Timber.DebugTree())
            }
        }
    }