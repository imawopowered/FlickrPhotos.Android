package com.mindera.flickergallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mindera.flickergallery.R
import com.mindera.flickergallery.model.Photo
import network.RetrofitService
import repository.PhotosRepository
import viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private val retrofitService = RetrofitService.getInstance()
    lateinit var viewModel: PhotosViewModel
    lateinit var photosList: MutableLiveData<List<Photo>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = PhotosViewModel(PhotosRepository(retrofitService))
        viewModel.photosList.observe(this, Observer {
            Log.d(TAG, "$it")
        })

        photosList = viewModel.getAllPhotos()

        for (item in photosList.value!!) {
            Log.d(TAG, "ID: ${item.id}, Title: ${item.title}")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
