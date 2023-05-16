package viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamanti.flickrphotos.model.Photo
import com.adamanti.flickrphotos.model.PhotosJson
import repository.PhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    private val allPhotosLiveDataJson = MutableLiveData<PhotosJson>()
    val allPhotosLiveDataList = MutableLiveData<List<Photo>>()

    fun getAllPhotos(): MutableLiveData<List<Photo>> {
        val response = repository.getAllPhotos()
        val errorMessage = MutableLiveData<String>()

        response.enqueue( object : Callback<PhotosJson> {
            override fun onResponse(call: Call<PhotosJson>, response: Response<PhotosJson>) {

                if(response.body() != null) {
                    allPhotosLiveDataJson.postValue(response.body())
                    allPhotosLiveDataList.postValue(response.body()?.photos?.photo)

                    Log.d(TAG, "${response.body()}")
                    Log.d(TAG, "Mapping for ${response.body()?.photos?.photo?.size} items...")
                }
            }

            override fun onFailure(call: Call<PhotosJson>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

        return allPhotosLiveDataList
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}