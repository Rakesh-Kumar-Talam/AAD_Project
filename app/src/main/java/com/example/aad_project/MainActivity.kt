package com.example.aad_project

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var arraylist: ArrayList<Message>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        arraylist= arrayListOf()

        recyclerView = findViewById(R.id.rv)
        recyclerView.adapter=Adapter(arraylist)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val rootView = findViewById<View>(android.R.id.content) // Get the root view of your activity
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            if (keypadHeight > screenHeight * 0.15) { // Adjust the threshold as needed
                val k=findViewById<View>(R.id.mv)
                var l=k.layoutParams
                val cp=l as ConstraintLayout.LayoutParams
                cp.setMargins(0,0,0,keypadHeight-130)
                l=cp
                k.setLayoutParams(l)
            } else {
                val k=findViewById<View>(R.id.mv)
                var l=k.layoutParams
                val cp=l as ConstraintLayout.LayoutParams
               cp.bottomMargin = 0
                l=cp
                k.setLayoutParams(l)
            }
        }
        val save=findViewById<ImageButton>(R.id.sendbtn)
        val enter_text=findViewById<EditText>(R.id.enter_text)
        save.setOnClickListener {
            if(enter_text.text.isEmpty()){
                enter_text.error="Enter Message"
            }
            else{
                arraylist.add(Message(enter_text.text.toString(),true))
                enter_text.setText("")
                recyclerView.adapter?.notifyDataSetChanged()
                recyclerView.scrollToPosition(arraylist.size-1)
            }
        }

    }
}