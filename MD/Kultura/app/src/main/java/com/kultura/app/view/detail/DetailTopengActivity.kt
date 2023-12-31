package com.kultura.app.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kultura.app.data.remote.ApiConfig
import com.kultura.app.data.repository.TopengRepository
import com.kultura.app.data.response.DetailTopengResponse
import com.kultura.app.databinding.ActivityDetailTopengBinding
import com.kultura.app.view.SearchTopengViewModelFactory

class DetailTopengActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTopengBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTopengBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val topengRepository = TopengRepository(apiService)

        val viewModelFactory = SearchTopengViewModelFactory(topengRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailTopengViewModel::class.java)

        val id = intent.getStringExtra(EXTRA_ID) ?: ""
        Log.d("Checked ID", id)


        binding.appBar.setNavigationOnClickListener{
            finish()
        }
        showLoading(true)
        viewModel.getDetailTopeng(id)
        viewModel.detailTopeng.observe(this) { topeng ->
            setDetailTopeng(topeng)
            showLoading(false)
        }
    }

    private fun setDetailTopeng(topeng: DetailTopengResponse?){
        Glide
            .with(this)
            .load(topeng?.imageUrl)
            .into(binding.ivDetail)

        binding.appBar.title = topeng?.name
        binding.tvDeskripsiDetail.text = topeng?.informasi
        binding.tvHargaDetail.text = topeng?.kisaranHarga
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID = "ID"
    }

}