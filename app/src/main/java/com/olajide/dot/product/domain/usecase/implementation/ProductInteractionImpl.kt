package com.olajide.dot.product.domain.usecase.implementation

import com.olajide.dot.product.domain.Repository
import com.olajide.dot.product.domain.usecase.GetProductUseCase
import com.olajide.dot.product.domain.usecase.interaction.ProductInteraction
import javax.inject.Inject


class ProductInteractionImpl @Inject constructor (private val repository : Repository):
    ProductInteraction {
    override fun provideLoginUseCase(): GetProductUseCase = GetProductUseCase(repository)
}