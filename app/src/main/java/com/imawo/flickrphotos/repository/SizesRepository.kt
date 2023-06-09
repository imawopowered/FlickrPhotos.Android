package com.imawo.flickrphotos.repository

import network.SizesRetrofitService

class SizesRepository(private val retrofitService: SizesRetrofitService) {
    fun getAllSizes(photoId: String) = retrofitService.getAllSizes(photoId)
}