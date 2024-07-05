package com.example.aad_project

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(){
    var data = arrayListOf("india is a big country with 3 sides water","english","android","computers")
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        recyclerview = findViewById(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        var dataAdapter = DataAdapter(data)
        recyclerview.adapter = dataAdapter

    }


}