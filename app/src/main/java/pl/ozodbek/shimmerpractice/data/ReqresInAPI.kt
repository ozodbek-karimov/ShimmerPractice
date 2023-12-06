package pl.ozodbek.shimmerpractice.data

import pl.ozodbek.shimmerpractice.data.models.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresInAPI {

    @GET("api/users")
    suspend fun getReqresInUsers(@Query("page") page: Int): List<Data>

}