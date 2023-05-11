package network

import com.mindera.flickergallery.model.SizesJson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import utils.Constants.BASE_URL_GET_SIZES

interface GetSizesRetrofitService {
    @GET("&api_key=9a95c68a9c6ec61104cd3967dcbb8bd3&{photo_id}&format=json&nojsoncallback=1")
    fun getAllSizes(@Query("photo_id") photoId: String): SizesJson

    companion object {

        var retrofitService: GetSizesRetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): GetSizesRetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_GET_SIZES)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(GetSizesRetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}