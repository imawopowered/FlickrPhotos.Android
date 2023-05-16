package repository

import network.PhotosRetrofitService

class PhotosRepository(private val retrofitService: PhotosRetrofitService) {
    fun getAllPhotos() = retrofitService.getAllPhotos()
}