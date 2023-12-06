package pl.ozodbek.shimmerpractice.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.ozodbek.shimmerpractice.adapters.ReqresInUsersAdapter
import pl.ozodbek.shimmerpractice.databinding.ActivityMainBinding
import pl.ozodbek.shimmerpractice.util.Resource
import pl.ozodbek.shimmerpractice.util.viewBinding
import pl.ozodbek.shimmerpractice.viewmodels.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val reqresInUsersAdapter: ReqresInUsersAdapter by lazy { ReqresInUsersAdapter() }
    private val viewModel:MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setupUI()
    }

    private fun setupUI() {
        setupActionBar()
        observeApiResponse()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerview.adapter = reqresInUsersAdapter
    }

    private fun observeApiResponse() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.commonPostResponseLiveData.observe(this@MainActivity){response ->
                when(response){
                    is Resource.Failure -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        reqresInUsersAdapter.submitList(response.value.data)
                    }
                }
            }
        }
    }

    private fun setupActionBar() {
        this.setSupportActionBar(binding.toolbar)
        this.title = "Shimmer Practice"
    }
}