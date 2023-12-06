package pl.ozodbek.shimmerpractice.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.ozodbek.shimmerpractice.adapters.CommonPostsAdapter
import pl.ozodbek.shimmerpractice.databinding.ActivityMainBinding
import pl.ozodbek.shimmerpractice.util.Resource
import pl.ozodbek.shimmerpractice.util.viewBinding
import pl.ozodbek.shimmerpractice.viewmodels.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val commonPostsAdapter: CommonPostsAdapter by lazy { CommonPostsAdapter() }
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
        binding.recyclerview.adapter = commonPostsAdapter
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
                        commonPostsAdapter.submitList(response.value)
                    }
                }
            }
        }
    }

    private fun setupActionBar() {

    }
}