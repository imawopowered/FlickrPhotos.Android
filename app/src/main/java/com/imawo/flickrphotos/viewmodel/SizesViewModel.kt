package com.imawo.flickrphotos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imawo.flickrphotos.model.Size
import com.imawo.flickrphotos.model.SizesJson
import com.imawo.flickrphotos.repository.SizesRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SizesViewModel(private val repository: SizesRepository) {
    private var _sizesLiveDataJson = MutableLiveData<SizesJson>()
    val sizesLiveDataJson
        get() = _sizesLiveDataJson

    private var _sizesLiveDataList = MutableLiveData<List<Size>>()
    val sizesLiveDataList
        get() = _sizesLiveDataList

    fun getAllSizes(photoId: String) {
        viewModelScope.launch {
            val response = repository.getAllSizes(photoId)
            val errorMessage = MutableLiveData<String>()

            response.enqueue(object : Callback<SizesJson> {
                override fun onResponse(call: Call<SizesJson>, response: Response<SizesJson>) {

                    if (response.body() != null) {
                        _sizesLiveDataJson.postValue(response.body())
                        _sizesLiveDataList.postValue(response.body()?.sizes?.size)

                        Log.d(TAG, "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<SizesJson>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

    companion object {
        private const val TAG = "SizesViewModel"
    }
}