package com.example.aad_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(var data: ArrayList<String?>): RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    lateinit var onClick:onitemclick
    interface onitemclick{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onitemclick){
        onClick = listener
    }
    class ViewHolder(view: View,onClick: onitemclick): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById<TextView>(R.id.textView2)
        val imageButton: ImageButton = view.findViewById<ImageButton>(R.id.imageButton5)
        init {
            view.setOnClickListener {
                onClick.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false),onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.textView.text = data[position]
        holder.imageButton.setOnClickListener {
            data.removeAt(position)
            notifyDataSetChanged()
        }
    }

}