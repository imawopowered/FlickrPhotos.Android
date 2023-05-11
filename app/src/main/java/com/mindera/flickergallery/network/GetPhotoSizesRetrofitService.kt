package network

import com.mindera.flickergallery.model.PhotosJson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import utils.Constants.BASE_URL_GET_SIZES

interface GetPhotoSizesRetrofitService {
    @GET("&api_key=9a95c68a9c6ec61104cd3967dcbb8bd3&photo_id=52882160209&format=json&nojsoncallback=1")
    fun getAllSizes(): PhotosJson

    companion object {

        var retrofitService: GetPhotosRetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): GetPhotosRetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_GET_SIZES)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(GetPhotosRetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}