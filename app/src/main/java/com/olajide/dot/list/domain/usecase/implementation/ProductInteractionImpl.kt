package com.olajide.dot.list.domain.usecase.implementation

import com.olajide.dot.list.domain.Repository
import com.olajide.dot.list.domain.usecase.GetProductUseCase
import com.olajide.dot.list.domain.usecase.interaction.ProductInteraction
import javax.inject.Inject


class ProductInteractionImpl @Inject constructor (private val repository : Repository):
    ProductInteraction {
    override fun provideLoginUseCase(): GetProductUseCase = GetProductUseCase(repository)
}