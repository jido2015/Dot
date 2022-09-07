package com.olajide.dot.product.domain.usecase

import com.olajide.dot.network.NetworkResult
import com.olajide.dot.product.domain.repository.FakeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class ProductUseCaseTest {
    @Mock
    private lateinit var prodictUsecase: GetProductUseCase

    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        prodictUsecase = Mockito.mock(GetProductUseCase::class.java)
    }

    @Test
    fun test_ProductUseCase_Success(){
        fakeRepository.shouldReturnError(true)

        runBlocking {
            Mockito.`when`(prodictUsecase.invoke()).thenReturn(NetworkResult.Success(""))
            
            val expected = fakeRepository.onProductReceived()
            
            Assert.assertEquals(expected.data, prodictUsecase.invoke().data )
        }
    }

}