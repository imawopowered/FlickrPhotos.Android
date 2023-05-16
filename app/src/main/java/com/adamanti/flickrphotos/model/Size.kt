package com.adamanti.flickrphotos.model

import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("height") val height: Int,
    @SerializedName("label") val label: String,
    @SerializedName("media") val media: String,
    @SerializedName("source") val source: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)