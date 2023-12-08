package com.kultura.app.view.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kultura.app.R
import com.kultura.app.data.local.Topeng

class SearchTopengActivity : AppCompatActivity() {

    private lateinit var rvTopeng: RecyclerView
    private val list = ArrayList<Topeng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_topeng)

        rvTopeng = findViewById(R.id.rv_topeng)
        rvTopeng.setHasFixedSize(true)


        list.addAll(getListTopeng())
        showRecyclerList()
    }

    fun getListTopeng(): ArrayList<Topeng> {
        val dataName = resources.getStringArray(R.array.data_topeng_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listTopeng = ArrayList<Topeng>()

        for (i in dataName.indices) {
            val player = Topeng(dataName[i], dataDescription[i])
            listTopeng.add(player)
        }
        return listTopeng
    }

    private fun showRecyclerList() {
        rvTopeng.layoutManager = LinearLayoutManager(this)
        val topengAdapter = TopengAdapter(list)
        rvTopeng.adapter = topengAdapter

        topengAdapter.setOnItemClickCallback(object : TopengAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Topeng) {
                showSelectedPlayer(data)
            }
        })
    }
    private fun showSelectedPlayer(topeng: Topeng) {
        Toast.makeText(this, "Kamu memilih " + topeng.name, Toast.LENGTH_SHORT).show()
    }



}