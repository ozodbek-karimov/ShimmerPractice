package pl.ozodbek.shimmerpractice.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.ozodbek.shimmerpractice.adapters.ReqresInUsersAdapter
import pl.ozodbek.shimmerpractice.databinding.ActivityMainBinding
import pl.ozodbek.shimmerpractice.util.Resource
import pl.ozodbek.shimmerpractice.util.setNullableAdapter
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



    /** SETTING UP ACTIONBAR */
    private fun setupActionBar() {
        this.setSupportActionBar(binding.toolbar)
        this.title = "Shimmer Practice"
    }



    /** SETTING UP RECYCLERVIEW */
    private fun setupRecyclerView() {
        binding.recyclerview.adapter = reqresInUsersAdapter
        binding.recyclerview.setNullableAdapter(adapter = reqresInUsersAdapter)
    }



    /** OBSERVING API RESPONSE */
    private fun observeApiResponse() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.commonPostResponseLiveData.observe(this@MainActivity){response ->
                when(response){
                    is Resource.Failure -> {
                        updateShimmerVisibility(showShimmer = false)
                    }
                    Resource.Loading -> {
                        updateShimmerVisibility(showShimmer = true)
                    }
                    is Resource.Success -> {
                        /** REMOVE DELAY LATER,I JUST ADD IT TO SHOW YOU SHIMMER */
                        lifecycleScope.launch {
                            delay(1500)
                            updateShimmerVisibility(showShimmer = false)
                            reqresInUsersAdapter.submitList(response.value.data)
                        }
                    }
                }
            }
        }
    }


    /** UPDATING VISIBILITY OF SHIMMER AND RECYCLERVIEW */
    private fun updateShimmerVisibility(showShimmer: Boolean) {
        val visibility = if (showShimmer) View.INVISIBLE else View.VISIBLE
        val shimmerVisibility = if (showShimmer) View.VISIBLE else View.INVISIBLE

        binding.recyclerview.visibility = visibility
        binding.shimmerLayout.visibility = shimmerVisibility

        if (showShimmer) {
            binding.shimmerLayout.startShimmer()
        } else {
            binding.shimmerLayout.stopShimmer()
        }
    }
}