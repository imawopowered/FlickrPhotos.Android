package com.imawo.flickrphotos.model

import com.google.gson.annotations.SerializedName

data class SizesJson(
    @SerializedName("sizes") val sizes: Sizes,
    @SerializedName("stat") val stat: String
)