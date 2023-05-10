package model
import com.google.gson.annotations.SerializedName

data class PhotosList(@SerializedName("Search") val mList : List<Photo>)