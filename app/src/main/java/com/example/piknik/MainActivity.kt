package com.example.piknik

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.piknik.R

class MainActivity : AppCompatActivity() {
    private lateinit var rvPiknik: RecyclerView
    private val list = ArrayList<Piknik>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPiknik = findViewById(R.id.rv_piknik)
        rvPiknik.setHasFixedSize(true)

        list.addAll(getListPiknik())
        showRecyclerList()
    }

    private fun getListPiknik(): ArrayList<Piknik> {

        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPiknik = ArrayList<Piknik>()
        for (i in dataName.indices) {
            val piknik = Piknik(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPiknik.add(piknik)
        }
        return listPiknik
    }

    private fun showRecyclerList() {
        rvPiknik.layoutManager = LinearLayoutManager(this)
        val listPiknikAdapter = ListPiknikAdapter(list)
        rvPiknik.adapter = listPiknikAdapter

        listPiknikAdapter.setOnItemClickCallback(object : ListPiknikAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Piknik) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intentToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentToAbout)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
