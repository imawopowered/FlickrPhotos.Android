package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import repository.PhotosRepository

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    val photosList = MutableLiveData<List<Photo>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {
        val response = repository.getAllPhotos()
    }
}