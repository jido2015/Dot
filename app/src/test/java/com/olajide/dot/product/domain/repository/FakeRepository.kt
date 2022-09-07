package com.olajide.dot.product.domain.repository

import com.olajide.dot.network.NetworkResult
import com.olajide.dot.product.data.model.Product
import com.olajide.dot.product.domain.Repository
import com.olajide.dot.product.domain.test_generator.TestDataGenerator

//Testing repository domain layer to ascertain that api response are properly handled.
class FakeRepository: Repository {

    private var shouldReturnError: Boolean? = false
    fun shouldReturnError(value: Boolean){
         shouldReturnError = value
    }

    override suspend fun onProductReceived(): NetworkResult<Product> {
        return when (shouldReturnError){
            false -> NetworkResult.Failure("Failed to get product list")

            true -> {
                //In this case, the return data is just an empty product because the product is
                // a little big as time do not permit me.
                NetworkResult.Success(
                    TestDataGenerator.getProduct()
                )
            }
            else -> {
                NetworkResult.Exception("Illegal argument Exception")
            }
        }
    }
}