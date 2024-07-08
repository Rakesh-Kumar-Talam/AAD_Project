package com.example.aad_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(){
    private var data:ArrayList<String?> = arrayListOf("india is a big country with 3 sides water","english","android","computers")
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_home)
        recyclerview = findViewById(R.id.recyclerview2)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        var dataAdapter = DataAdapter(data)
        recyclerview.adapter = dataAdapter
    }


}