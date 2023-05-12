package com.mindera.flickergallery.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.mindera.flickergallery.R
import com.mindera.flickergallery.model.PhotoToDisplay

class PhotosAdapter(
    private val photosList: List<PhotoToDisplay>,
    private val context: Context,
    private val clickListener: (PhotoToDisplay, Int) -> Unit) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutIdForListItem = R.layout.layout_photos
        val inflater = LayoutInflater.from(context)
        val shouldAttach = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttach)
        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {

        val item: PhotoToDisplay = photosList[position]
        holder.bind(item, position, clickListener)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(item: PhotoToDisplay, position: Int, clickListener: (PhotoToDisplay, Int) -> Unit) {

            Glide.with(itemView)
                .load(item.source)
                .transform(CenterCrop())
                .into(image)

            itemView.setOnClickListener {
                clickListener(item, position)
            }
        }
    }
}