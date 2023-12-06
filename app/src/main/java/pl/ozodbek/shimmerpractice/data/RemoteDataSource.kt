package pl.ozodbek.shimmerpractice.data


import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val profexAPI: ReqresInAPI,

    ) : SafeApiCall {

    suspend fun getReqresInUsers(page: Int) = safeApiCall { profexAPI.getReqresInUsers(page) }


}