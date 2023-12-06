package pl.ozodbek.shimmerpractice.data

import pl.ozodbek.shimmerpractice.data.models.CommonPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("api/common/posts")
    suspend fun getCommonPosts(@Query("page") page: Int): List<CommonPosts>

}