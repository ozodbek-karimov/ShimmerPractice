package pl.ozodbek.shimmerpractice.data


import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val profexAPI: ApiClient,

    ) : SafeApiCall {

    suspend fun getCommonPosts(page: Int) = safeApiCall { profexAPI.getCommonPosts(page) }


}