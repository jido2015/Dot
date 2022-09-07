package com.olajide.dot.product.domain.usecase

import com.olajide.dot.product.domain.Repository
import javax.inject.Inject

class GetProductUseCase @Inject constructor (private val repository: Repository){
    suspend operator fun invoke() = repository.onProductReceived()
}