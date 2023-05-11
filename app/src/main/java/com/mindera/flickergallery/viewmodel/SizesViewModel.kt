package com.mindera.flickergallery.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mindera.flickergallery.model.Size
import com.mindera.flickergallery.model.SizesJson
import com.mindera.flickergallery.repository.SizesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewmodel.PhotosViewModel

class SizesViewModel(private val repository: SizesRepository) {
    private val sizesLiveDataJson = MutableLiveData<SizesJson>()
    private val sizesLiveDataList = MutableLiveData<List<Size>>()

    fun getAllSizes(photoId: String): MutableLiveData<List<Size>> {
        val response = repository.getAllSizes(photoId)
        val errorMessage = MutableLiveData<String>()

        Log.d(TAG, "Getting sizes...")

        response.enqueue( object : Callback<SizesJson>{
            override fun onResponse(call: Call<SizesJson>?, response: Response<SizesJson>?) {

                if(response?.body() != null)
                    sizesLiveDataJson.postValue(response.body())
            }

            override fun onFailure(call: Call<SizesJson>, t: Throwable) {
                if (t != null) {
                    errorMessage.postValue(t.message)
                }
            }
        })

        val sizesList = sizesLiveDataJson.value?.sizes?.size
        sizesLiveDataList.postValue(sizesList)

        return sizesLiveDataList
    }

    companion object {
        private const val TAG = "SizesViewModel"
    }
}