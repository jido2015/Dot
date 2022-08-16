package com.olajide.dot.list.data

import com.olajide.dot.list.data.model.Product
import com.olajide.dot.list.domain.Repository
import com.olajide.dot.network.NetworkResult
import com.olajide.dot.network.retrofit.DotApiService
import timber.log.Timber
import javax.inject.Inject

//Data source interacts with repository to call data from remote api and local database.
class RemoteDataSource @Inject constructor(private val api: DotApiService): Repository {
    override suspend fun onProductReceived(): NetworkResult<Product> {
        return try {
            val response = api.getProducts()

            val result = response.body()
            if (response.isSuccessful && result != null) {
                NetworkResult.Success(result)
            }else {
                val errorMessage =  response.toString()
                Timber.d(errorMessage)
                NetworkResult.Failure(errorMessage)
            }
        } catch (e: Exception) {
            NetworkResult.Exception(e.message ?: "An error occurred")
        }
    }
}