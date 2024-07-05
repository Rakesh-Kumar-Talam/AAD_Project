package com.example.demo2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aad_project.R
import com.example.vitbatch2.DataAdaper

class HomeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var data = arrayListOf("india is a big country with 3 sides water","english","android","computers")
    lateinit var recyclerview: RecyclerView
    lateinit var btn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        var dataAdapter = DataAdaper(data)
        recyclerview.adapter = dataAdapter

    }
    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item = adapter?.selectedItem.toString()

    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

//    fun delete(view: View) {
//        return deleteitem
//    }

}