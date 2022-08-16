package com.olajide.dot.list.domain.usecase.interaction

import com.olajide.dot.list.domain.usecase.GetProductUseCase

/**
 * An interaction interface to return provide  access
 * GetProductUseCase to invoke api call in data source
 */
interface ProductInteraction {
    fun  provideLoginUseCase(): GetProductUseCase
}