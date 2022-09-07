package com.olajide.dot.product.domain.repository

import com.olajide.dot.network.NetworkResult
import com.olajide.dot.product.data.model.Product
import com.olajide.dot.product.domain.Repository

class FakeRepository: Repository {

    private var shouldReturnError: Boolean? = false
    fun shouldReturnError(value: Boolean){
         shouldReturnError = value
    }

    override suspend fun onProductReceived(): NetworkResult<Product> {
        return when (shouldReturnError){
            false -> NetworkResult.Failure("Failed to get product list")

            true -> {
                NetworkResult.Success(
                    Product()
                )
            }
            else -> {
                NetworkResult.Exception("Illegal argument Exception")
            }
        }
    }
}