package com.kultura.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kultura.app.R

class DetailTopengActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TOPENG = "extra_topeng"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_topeng)
    }
}