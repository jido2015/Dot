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

            Timber.d("Reached Datasource")

            val response = api.getProducts()

            val result = response.body()
            if (response.isSuccessful && result != null) {
                NetworkResult.Success(result)
            }else {
                Timber.d("ApiError $response")
                NetworkResult.Failure(response.toString())
            }
        } catch (e: Exception) {
            Timber.d("ApiError $e")
            NetworkResult.Exception(e.message ?: "An error occurred")
        }
    }
}