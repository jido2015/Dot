package com.olajide.dot.list.domain.usecase

import com.olajide.dot.list.domain.Repository
import javax.inject.Inject

class GetProductUseCase @Inject constructor (private val repository: Repository){
    suspend operator fun invoke() = repository.onProductReceived()
}