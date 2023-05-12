package com.mindera.flickergallery

import network.PhotosRetrofitService
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import repository.PhotosRepository
import viewmodel.PhotosViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class TestViewModel {

    private val viewModel = PhotosViewModel(PhotosRepository(PhotosRetrofitService.getInstance()))

    @Test
    fun testPhotoViewModel() {
        viewModel.photoToDisplayLiveDataList.observeForever {}
        viewModel.getAllPhotos()

        assertEquals(0, viewModel.photoToDisplayLiveDataList.value?.size)
    }
}
