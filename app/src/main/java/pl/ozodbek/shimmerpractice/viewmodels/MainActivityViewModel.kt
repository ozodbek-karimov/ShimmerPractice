package pl.ozodbek.shimmerpractice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.ozodbek.shimmerpractice.data.Repository
import pl.ozodbek.shimmerpractice.data.models.CommonPosts
import pl.ozodbek.shimmerpractice.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _commonPostResponseLiveData = MutableLiveData<Resource<List<CommonPosts>>>()
    val commonPostResponseLiveData: LiveData<Resource<List<CommonPosts>>> get() = _commonPostResponseLiveData

    init {
        getCommonPost()
    }

    private fun getCommonPost() = viewModelScope.launch {
        _commonPostResponseLiveData.postValue(Resource.Loading)
        val response = repository.remote.getCommonPosts(10)
        _commonPostResponseLiveData.postValue(response)
    }

}