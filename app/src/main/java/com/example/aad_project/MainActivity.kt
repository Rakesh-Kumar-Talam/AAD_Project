package com.example.aad_project

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // val x_rapidapi_key=BuildConfig.api_key
    lateinit var recyclerView: RecyclerView
    lateinit var arraylist: ArrayList<MessageX>
    var QueryHandler=QueryHandler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // const val x_rapidapi_key=BuildConfig.api_key
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
                val message=enter_text.text.toString()

                arraylist.add(MessageX(message,true))
                enter_text.setText("")
                Thread{

                    var answer= QueryHandler.createChatCompletion(message).toString()
                    arraylist.add(MessageX(answer,false))
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.d("TAG" , "onCreate: $answer")

                }.start()
                recyclerView.scrollToPosition(arraylist.size-1)

            }
        }

    }
}