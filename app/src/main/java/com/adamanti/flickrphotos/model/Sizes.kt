package com.adamanti.flickrphotos.model

import com.google.gson.annotations.SerializedName

data class Sizes(
    @SerializedName("canblog") val canblog: Int,
    @SerializedName("candownload") val candownload: Int,
    @SerializedName("canprint") val canprint: Int,
    @SerializedName("size") val size: List<Size>
)