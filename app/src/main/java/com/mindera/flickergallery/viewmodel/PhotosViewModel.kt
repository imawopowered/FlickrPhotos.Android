package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import com.mindera.flickergallery.model.PhotoToDisplay
import repository.GetPhotosRepository

class PhotosViewModel(private val repository: GetPhotosRepository): ViewModel() {
    val photoToDisplayLiveDataList = MutableLiveData<List<PhotoToDisplay>>()

    fun getAllPhotos(): MutableLiveData<List<PhotoToDisplay>> {
        val response = repository.getAllPhotos()
        val photoToDisplayList = response.photos.photo.map {
            photo: Photo -> PhotoToDisplay(
                id = photo.id,
                title = photo.title,
                size = "",
                url = ""
            )
        }

        photoToDisplayLiveDataList.postValue(photoToDisplayList)

        return photoToDisplayLiveDataList
    }
}