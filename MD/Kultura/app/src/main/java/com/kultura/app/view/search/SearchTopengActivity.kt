package com.kultura.app.view.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kultura.app.data.remote.ApiConfig
import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.data.response.TopengItem
import com.kultura.app.databinding.ActivitySearchTopengBinding
import com.kultura.app.view.SearchTopengViewModelFactory

class SearchTopengActivity : AppCompatActivity() {




    private lateinit var binding: ActivitySearchTopengBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTopengBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val topengRepository = TopengRepository(apiService)

        val viewModelFactory = SearchTopengViewModelFactory(topengRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchTopengViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvTopeng.layoutManager = layoutManager

        binding.appBar.setNavigationOnClickListener{
            finish()
        }

        viewModel.listTopeng.observe(this) {listTopeng ->
            setTopengData(listTopeng)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }



    }

    private fun setTopengData(data: List<TopengItem>) {
        val adapter = TopengAdapter()
        adapter.submitList(data)
        binding.rvTopeng.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}