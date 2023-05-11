package repository

import network.GetPhotosRetrofitService

class GetPhotosRepository(private val retrofitService: GetPhotosRetrofitService) {
    fun getAllPhotos() = retrofitService.getAllPhotos()
}