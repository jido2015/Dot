package com.olajide.dot.product.domain

import com.olajide.dot.product.data.model.Product
import com.olajide.dot.network.NetworkResult

interface Repository {
    suspend fun onProductReceived(): NetworkResult<Product>

}