package com.mindera.flickergallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindera.flickergallery.R
import com.mindera.flickergallery.adapters.PhotosAdapter
import com.mindera.flickergallery.databinding.ActivityMainBinding
import com.mindera.flickergallery.model.PhotoToDisplay
import network.GetPhotosRetrofitService
import repository.GetPhotosRepository
import viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val getPhotosRetrofitService = GetPhotosRetrofitService.getInstance()
    lateinit var photosViewModel: PhotosViewModel
    lateinit var photosAdapter: PhotosAdapter

    private var photosRecyclerView:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photosViewModel = PhotosViewModel(GetPhotosRepository(getPhotosRetrofitService))
        photosViewModel.photoToDisplayLiveDataList.observe(this, Observer {
            Log.d(TAG, "$it")
            loadPhotos(it)
        })

        photosViewModel.getAllPhotos()

        photosRecyclerView= binding.photosRecyclerView
        photosRecyclerView?.layoutManager = GridLayoutManager(this, 2)
    }

    fun loadPhotos(items: List<PhotoToDisplay>) {
        photosAdapter = PhotosAdapter(items, this, { item, position ->
            // item...
        })

        photosRecyclerView?.adapter = photosAdapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
