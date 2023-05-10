package network

import com.mindera.flickergallery.model.PhotosJson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import utils.Constants.BASE_URL

interface RetrofitService {
    @GET("?method=flickr.photos.search&api_key=9a95c68a9c6ec61104cd3967dcbb8bd3&tags=snail&page=1&format=json&nojsoncallback=1")
    fun getAllPhotos(): PhotosJson

    companion object {

        var retrofitService: RetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}