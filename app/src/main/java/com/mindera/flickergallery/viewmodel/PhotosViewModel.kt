package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import com.mindera.flickergallery.model.PhotosJson
import repository.PhotosRepository
import retrofit2.Call

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    val photosList = MutableLiveData<List<Photo>>()
    val photoJson = MutableLiveData<Call<PhotosJson>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {
        return photoJson.postValue(repository.getAllPhotos())
    }
}