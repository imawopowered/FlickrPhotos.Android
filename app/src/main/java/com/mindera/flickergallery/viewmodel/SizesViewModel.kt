package com.mindera.flickergallery.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mindera.flickergallery.model.Size
import com.mindera.flickergallery.repository.GetSizesRepository

class SizesViewModel(private val repository: GetSizesRepository) {
    private val sizesLiveDataList = MutableLiveData<List<Size>>()

    fun getAllSizes(): MutableLiveData<List<Size>> {
        val response = repository.getAllSizes()
        val sizesList = response.sizes.size
        sizesLiveDataList.postValue(sizesList)

        return sizesLiveDataList
    }
}