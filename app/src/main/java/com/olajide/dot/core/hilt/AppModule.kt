package com.olajide.dot.core.hilt

import com.olajide.dot.core.DispatchProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * An Application module object to provide single instance of needed classes.
 * The below implementation provides single instance of functions using Hilt Dependency
 * Injection without the need to instantiate them when needed
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Dispatchers - Use this dispatcher to run a coroutine on the main or background Android thread.
    @Singleton
    @Provides
    fun provideDispatcher(): DispatchProvider = object : DispatchProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}