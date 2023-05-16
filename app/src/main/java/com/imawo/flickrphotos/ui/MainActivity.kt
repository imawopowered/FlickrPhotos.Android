package com.imawo.flickrphotos.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imawo.flickrphotos.adapters.PhotosAdapter
import com.imawo.flickrphotos.databinding.ActivityMainBinding
import com.imawo.flickrphotos.helpers.Utilities
import com.imawo.flickrphotos.model.AllPhotos
import com.imawo.flickrphotos.repository.SizesRepository
import com.imawo.flickrphotos.viewmodel.SizesViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import network.PhotosRetrofitService
import network.SizesRetrofitService
import repository.PhotosRepository
import viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val photosRetrofitService = PhotosRetrofitService.getInstance()
    private val sizeRetrofitService = SizesRetrofitService.getInstance()

    lateinit var photosViewModel: PhotosViewModel
    lateinit var sizesViewModel: SizesViewModel
    lateinit var photosAdapter: PhotosAdapter

    private var photosRecyclerView:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Adamanti LAB: Flickr Photos")

        val decorView = window.decorView
        val windowBackground = decorView.background
        Utilities.setupBlurView(this, binding.rootView, windowBackground, binding.blurView, 24F)

        photosViewModel = PhotosViewModel(PhotosRepository(photosRetrofitService))
        sizesViewModel = SizesViewModel(SizesRepository(sizeRetrofitService))

        val photoToDisplay: MutableList<AllPhotos> = mutableListOf<AllPhotos>()

        photosViewModel.allPhotosLiveDataList.observe(this) { items ->
            if (items != null) {

                for (item in items) {
                    sizesViewModel.getAllSizes(item.id)
                }
            }
        }

        sizesViewModel.sizesLiveDataList.observe(this) { items ->
            if (items != null) {
                for (item in items) {
                    Log.d(TAG, "Item size type: ${item.label}")

                    photoToDisplay.add(
                        AllPhotos(
                            label = item.label,
                            title = "",
                            source = item.source
                        )
                    )
                }

                loadPhotos(photoToDisplay)
            }
        }

        photosViewModel.getAllPhotos()

        photosRecyclerView= binding.photosRecyclerView
        photosRecyclerView?.layoutManager = GridLayoutManager(this, 2)
    }

    fun loadPhotos(items: List<AllPhotos>) {
        photosAdapter = PhotosAdapter(items, this) { item, position ->

            Glide.with(this@MainActivity)
                .load(item.source)
                .into(object : CustomTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        binding.rootView.setBackground(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }

        photosRecyclerView?.adapter = photosAdapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
