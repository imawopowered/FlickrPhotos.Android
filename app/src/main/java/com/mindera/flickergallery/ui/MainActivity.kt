package com.mindera.flickergallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.mindera.flickergallery.R
import network.RetrofitService
import repository.PhotosRepository
import viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private val retrofitService = RetrofitService.getInstance()
    lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = PhotosViewModel(PhotosRepository(retrofitService))
        viewModel.photosList.observe(this, Observer {
            Log.d(TAG, "$it")
        })
        viewModel.getAllPhotos()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
