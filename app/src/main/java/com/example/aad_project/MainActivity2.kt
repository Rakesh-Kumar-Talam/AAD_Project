package com.example.aad_project

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    private var data:ArrayList<String?> = arrayListOf("india is a big country with 3 sides water","english","android","computers")
    private lateinit var recyclerview: RecyclerView
    private var dataAdapter = DataAdapter(data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //actionBar?.hide()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerview = findViewById(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = dataAdapter
        dataAdapter.setOnItemClickListener(object : DataAdapter.onitemclick {
            override fun onItemClick(position: Int) {
                Log.i("tag","create")
                Intent(this@MainActivity2 , MainActivity3::class.java).also {
                    it.putExtra("name" , data[position])
                    startActivity(it)
                }
            }
        }
        )
        val add_button:Button=findViewById(R.id.button_new_chat)
        add_button.setOnClickListener {
            var name="new item"
            var count=0
            while((name in data)){
                count++
                name += " $count"
            }
            showDialogNameEditor(name)
        dataAdapter.notifyDataSetChanged()
        }
    }

    private fun showDialogNameEditor(name: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.name_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val nameEditor: EditText = dialog.findViewById(R.id.enter_name)
        val saveButton: Button = dialog.findViewById(R.id.savename)
        val cancelButton: Button = dialog.findViewById(R.id.cancel)
        saveButton.setOnClickListener {
            val newName = nameEditor.text.toString()
             if (newName .isEmpty()) {
                data.add(name)
            }
             else{
                data.add(newName )
             }
            dataAdapter.notifyItemInserted(data.size-1)
            dialog.dismiss()
            Intent(this,MainActivity3::class.java).also {
                it.putExtra("name",data.last())
                startActivity(it)
            }
        }
        cancelButton.setOnClickListener {
            data.add(name)
            dataAdapter.notifyItemInserted(data.size-1)
            dialog.dismiss()
            Intent(this,MainActivity3::class.java).also {
                it.putExtra("name",data.last())
                startActivity(it)
            }
        }
        dialog.show()
    }
}


