package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindera.flickergallery.model.Photos
import model.PhotosList
import repository.PhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(private val repository: PhotosRepository): ViewModel() {
    val photosList = MutableLiveData<List<Photos>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {
        val response = repository.getAllPhotos()
        response.enqueue(object : Callback<PhotosList> {
            override fun onResponse(call: Call<PhotosList>, response: Response<PhotosList>) {
                photosList.postValue(response.body()?.mList)
            }

            override fun onFailure(call: Call<PhotosList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}