package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photos
import com.mindera.flickergallery.model.PhotosList
import repository.PhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    val photosList = MutableLiveData<List<Photos>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {
        val response = repository.getAllPhotos()
    }
}