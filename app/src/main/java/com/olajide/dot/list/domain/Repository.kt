package com.olajide.dot.list.domain

import com.olajide.dot.list.data.model.Product
import com.olajide.dot.network.NetworkResult

interface Repository {
    suspend fun onProductReceived(): NetworkResult<Product>

}