package viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photo
import com.mindera.flickergallery.model.PhotoToDisplay
import com.mindera.flickergallery.model.Photos
import com.mindera.flickergallery.model.PhotosJson
import com.mindera.flickergallery.model.SizesJson
import com.mindera.flickergallery.ui.MainActivity
import repository.GetPhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: GetPhotosRepository): ViewModel() {
    val photoToDisplayLiveDataJson = MutableLiveData<Photos>()
    val photoToDisplayLiveDataList = MutableLiveData<List<PhotoToDisplay>>()

    fun getAllPhotos(): MutableLiveData<List<PhotoToDisplay>> {
        val response = repository.getAllPhotos()
        val errorMessage = MutableLiveData<String>()

        response.enqueue( object : Callback<PhotosJson> {
            override fun onResponse(call: Call<PhotosJson>?, response: Response<PhotosJson>?) {

                if(response?.body() != null) {
                    photoToDisplayLiveDataJson.postValue(response.body()?.photos)

                    Log.d(TAG, "${response.body()}")
                    Log.d(TAG, "Mapping to Photo model for ${photoToDisplayLiveDataJson.value?.photo?.size} items...")

                    val photoToDisplayList = photoToDisplayLiveDataJson.value?.photo?.map { photo: Photo ->
                        PhotoToDisplay(
                            id = photo.id,
                            title = photo.title,
                            size = "",
                            url = ""
                        )
                    }

                    photoToDisplayLiveDataList.postValue(photoToDisplayList)
                }
            }

            override fun onFailure(call: Call<PhotosJson>, t: Throwable) {
                if (t != null) {
                    errorMessage.postValue(t.message)
                    Log.d(TAG, "EROARE: ${t.message}")
                }
            }
        })

        return photoToDisplayLiveDataList
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}