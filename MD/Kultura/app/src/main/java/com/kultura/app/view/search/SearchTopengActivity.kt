package com.kultura.app.view.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
        val topengRepository = TopengRepository(apiService) // Create an instance of TopengRepository

        val viewModelFactory = SearchTopengViewModelFactory(topengRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchTopengViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvTopeng.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvTopeng.addItemDecoration(itemDecoration)


        viewModel.listTopeng.observe(this) {listTopeng ->
            setTopengData(listTopeng)
        }

    }

    private fun setTopengData(data: List<TopengItem>) {
        val adapter = TopengAdapter()
        adapter.submitList(data)
        binding.rvTopeng.adapter = adapter
    }

}