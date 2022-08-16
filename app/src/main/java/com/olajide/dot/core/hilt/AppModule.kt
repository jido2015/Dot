package com.olajide.dot.core.hilt

import com.olajide.dot.list.data.RemoteDataSource
import com.olajide.dot.list.domain.Repository
import com.olajide.dot.list.domain.usecase.implementation.ProductInteractionImpl
import com.olajide.dot.list.domain.usecase.interaction.ProductInteraction
import com.olajide.dot.network.retrofit.provideGenericApiService
import com.olajide.dot.network.retrofit.DotApiService
import com.olajide.dot.network.retrofit.qualifiers.InterceptorOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * An Application module object to provide single instance of needed classes.
 * The below implementation provides single instance of functions using Hilt Dependency
 * Injection without the need to instantiate them when needed
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @InterceptorOkHttpClient
    @Singleton
    @Provides
    fun provideHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideDotApiService(@InterceptorOkHttpClient okHttpClient: OkHttpClient): DotApiService =
        provideGenericApiService(okHttpClient)


    @Singleton
    @Provides
    fun provideProductRepository(api: DotApiService):Repository = RemoteDataSource(api)


    //Providing Interaction for Product api
    @Singleton
    @Provides
    fun provideProductInteractor(repository: Repository): ProductInteraction =
        ProductInteractionImpl(repository)

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