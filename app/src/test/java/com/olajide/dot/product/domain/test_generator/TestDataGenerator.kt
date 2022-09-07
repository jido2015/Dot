package com.olajide.dot.product.domain.test_generator

import com.olajide.dot.product.data.model.Product

// The test generator is where we generate products
// data for the fake repository to return to our data source.
// But i wont be adding the actual fake data due to time.
class TestDataGenerator {

    companion object {
        fun getProduct(): Product {
            return Product()
        }
    }

    }