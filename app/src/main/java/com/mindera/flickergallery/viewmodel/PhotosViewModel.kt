package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import repository.PhotosRepository

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    val photosList = MutableLiveData<List<Photo>>()

    fun getAllPhotos(): MutableLiveData<List<Photo>> {
        val response = repository.getAllPhotos()
        photosList.postValue(response.photos.photo)
        return photosList
    }
}