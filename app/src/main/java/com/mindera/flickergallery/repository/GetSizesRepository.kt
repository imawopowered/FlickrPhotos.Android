package com.mindera.flickergallery.repository

import network.SizesRetrofitService

class GetSizesRepository(private val retrofitService: SizesRetrofitService) {
    fun getAllSizes() = retrofitService.getAllSizes("52882160209")
}