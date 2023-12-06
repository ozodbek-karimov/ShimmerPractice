package pl.ozodbek.shimmerpractice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.ozodbek.shimmerpractice.data.Repository
import pl.ozodbek.shimmerpractice.data.models.Data
import pl.ozodbek.shimmerpractice.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _commonPostResponseLiveData = MutableLiveData<Resource<List<Data>>>()
    val commonPostResponseLiveData: LiveData<Resource<List<Data>>> get() = _commonPostResponseLiveData

    init {
        getReqresInUsers()
    }

    private fun getReqresInUsers() = viewModelScope.launch {
        _commonPostResponseLiveData.postValue(Resource.Loading)
        val response = repository.remote.getReqresInUsers(10)
        _commonPostResponseLiveData.postValue(response)
    }

}