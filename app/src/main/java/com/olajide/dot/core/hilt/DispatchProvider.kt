package com.olajide.dot.core.hilt

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Dispatchers - Use this dispatcher to run a coroutine on the main or background Android thread.
 */
interface DispatchProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}