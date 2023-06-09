package network

import com.imawo.flickrphotos.model.SizesJson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import utils.Constants.API_KEY
import utils.Constants.BASE_URL_GET_SIZES

interface SizesRetrofitService {
    @GET("?method=flickr.photos.getSizes&api_key=$API_KEY&format=json&nojsoncallback=1")
    fun getAllSizes(@Query(value = "photo_id") photoId: String): Call<SizesJson>

    companion object {

        var retrofitService: SizesRetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): SizesRetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_GET_SIZES)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(SizesRetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}