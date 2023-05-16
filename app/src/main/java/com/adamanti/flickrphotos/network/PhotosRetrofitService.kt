package network

import com.adamanti.flickrphotos.model.PhotosJson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import utils.Constants.API_KEY
import utils.Constants.BASE_URL_GET_PHOTOS

interface PhotosRetrofitService {
    @GET("?method=flickr.photos.search&api_key=$API_KEY&tags=snail&page=1&format=json&nojsoncallback=1")
    fun getAllPhotos(): Call<PhotosJson>

    companion object {

        var retrofitService: PhotosRetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): PhotosRetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_GET_PHOTOS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(PhotosRetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}