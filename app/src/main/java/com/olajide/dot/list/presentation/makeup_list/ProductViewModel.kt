package com.olajide.dot.list.presentation.makeup_list

import androidx.lifecycle.ViewModel
import com.olajide.dot.extension.launchIo
import com.olajide.dot.list.data.model.Product
import com.olajide.dot.list.domain.usecase.interaction.ProductInteraction
import com.olajide.dot.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val interaction: ProductInteraction): ViewModel() {

    //StateFow
    private val _state = MutableSharedFlow<NetworkResult<Product>>()
    val state = _state.asSharedFlow()


    fun getProductList(){
        launchIo {

            Timber.d( "launchIo called")
            _state.emit(NetworkResult.Loading())
            when (val response = interaction.provideLoginUseCase().invoke()) {
                is NetworkResult.Failure -> {

                    _state.emit(NetworkResult.Failure(response.errorText.toString()))
                }
                is NetworkResult.Success -> {
                    _state.emit(NetworkResult.Success(response.data!!))
                }
                is NetworkResult.Exception -> {
                    Timber.d( "launchIoException"+response.errorText.toString())
                    _state.emit(NetworkResult.Failure(response.errorText.toString()))
                }
                else -> {}
            }
        }
    }
}