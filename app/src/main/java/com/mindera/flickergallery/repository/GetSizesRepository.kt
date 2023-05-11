package com.mindera.flickergallery.repository

import network.GetSizesRetrofitService

class GetSizesRepository(private val retrofitService: GetSizesRetrofitService) {
    fun getAllSizes() = retrofitService.getAllSizes()
}