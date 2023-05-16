package com.adamanti.flickrphotos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.adamanti.flickrphotos.model.Size
import com.adamanti.flickrphotos.model.SizesJson
import com.adamanti.flickrphotos.repository.SizesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SizesViewModel(private val repository: SizesRepository) {
    private val sizesLiveDataJson = MutableLiveData<SizesJson>()
    val sizesLiveDataList = MutableLiveData<List<Size>>()

    fun getAllSizes(photoId: String): MutableLiveData<List<Size>> {
        val response = repository.getAllSizes(photoId)
        val errorMessage = MutableLiveData<String>()

        response.enqueue( object : Callback<SizesJson>{
            override fun onResponse(call: Call<SizesJson>, response: Response<SizesJson>) {

                if(response.body() != null) {
                    sizesLiveDataJson.postValue(response.body())
                    sizesLiveDataList.postValue(response.body()?.sizes?.size)

                    Log.d(TAG, "${response.body()}")
                }
            }

            override fun onFailure(call: Call<SizesJson>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

        return sizesLiveDataList
    }

    companion object {
        private const val TAG = "SizesViewModel"
    }
}