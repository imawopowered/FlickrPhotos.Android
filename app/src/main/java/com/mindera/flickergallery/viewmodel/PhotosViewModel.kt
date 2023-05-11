package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import com.mindera.flickergallery.model.PhotoToDisplay
import com.mindera.flickergallery.model.PhotosJson
import com.mindera.flickergallery.model.SizesJson
import repository.GetPhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: GetPhotosRepository): ViewModel() {
    val photoToDisplayLiveDataJson = MutableLiveData<PhotosJson>()
    val photoToDisplayLiveDataList = MutableLiveData<List<PhotoToDisplay>>()

    fun getAllPhotos(): MutableLiveData<List<PhotoToDisplay>> {
        val response = repository.getAllPhotos()
        val errorMessage = MutableLiveData<String>()

        response.enqueue( object : Callback<PhotosJson> {
            override fun onResponse(call: Call<PhotosJson>?, response: Response<PhotosJson>?) {

                if(response?.body() != null)
                    photoToDisplayLiveDataJson.postValue(response.body())
            }

            override fun onFailure(call: Call<PhotosJson>, t: Throwable) {
                if (t != null) {
                    errorMessage.postValue(t.message)
                }
            }
        })

        val photoToDisplayList = photoToDisplayLiveDataJson.value?.photos?.photo?.map {
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