package com.olajide.dot.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Application class to keep the global state of our app.
@HiltAndroidApp
class DotBaseApplication: Application()