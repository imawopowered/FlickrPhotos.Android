package repository

import network.PhotosRetrofitService

class GetPhotosRepository(private val retrofitService: PhotosRetrofitService) {
    fun getAllPhotos() = retrofitService.getAllPhotos()
}