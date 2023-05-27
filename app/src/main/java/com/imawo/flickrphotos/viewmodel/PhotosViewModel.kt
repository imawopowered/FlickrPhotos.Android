package viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imawo.flickrphotos.model.Photo
import com.imawo.flickrphotos.model.PhotosJson
import repository.PhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    private var _allPhotosLiveDataJson = MutableLiveData<PhotosJson>()
    val allPhotosLiveDataJson
        get() = _allPhotosLiveDataJson

    private var _allPhotosLiveDataList = MutableLiveData<List<Photo>>()
    val  allPhotosLiveDataList
        get() = _allPhotosLiveDataList

    fun getAllPhotos() {
        val response = repository.getAllPhotos()
        val errorMessage = MutableLiveData<String>()

        response.enqueue( object : Callback<PhotosJson> {
            override fun onResponse(call: Call<PhotosJson>, response: Response<PhotosJson>) {

                if(response.body() != null) {
                    _allPhotosLiveDataJson.postValue(response.body())
                    _allPhotosLiveDataList.postValue(response.body()?.photos?.photo)

                    Log.d(TAG, "${response.body()}")
                    Log.d(TAG, "Mapping for ${response.body()?.photos?.photo?.size} items...")
                }
            }

            override fun onFailure(call: Call<PhotosJson>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}