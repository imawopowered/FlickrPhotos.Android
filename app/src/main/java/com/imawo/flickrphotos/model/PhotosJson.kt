package com.imawo.flickrphotos.model

import com.google.gson.annotations.SerializedName

data class PhotosJson(
    @SerializedName("photos") val photos: Photos,
    @SerializedName("stat") val stat: String
)