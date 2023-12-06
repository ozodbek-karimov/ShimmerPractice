package pl.ozodbek.shimmerpractice.data

import dagger.hilt.android.scopes.ViewModelScoped
import pl.ozodbek.shimmerpractice.data.RemoteDataSource
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {
    val remote = remoteDataSource
}