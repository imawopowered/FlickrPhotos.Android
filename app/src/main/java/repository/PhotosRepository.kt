package repository

import network.RetrofitService

class PhotosRepository(private val retrofitService: RetrofitService) {
    fun getAllPhotos() = retrofitService.getAllPhotos()
}