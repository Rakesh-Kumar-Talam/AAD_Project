package com.example.vitbatch2

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.aad_project.R
import java.nio.file.Files.delete

class DataAdaper(var data: ArrayList<String>) : RecyclerView.Adapter<DataAdaper.DataViewHolder>() {

    var TAG = DataAdaper::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        var rowPlank = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row3,parent,false)
        return DataViewHolder(rowPlank)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.tvRowItem.setText(data[position])
        holder.button.setOnClickListener{
            delete(position:int)
        }
    }



    class DataViewHolder(rowPlank:View):RecyclerView.ViewHolder(rowPlank) {
        init {

        }
        var tvRowItem = itemView.findViewById<TextView>(R.id.textView2)
        val button=itemView.findViewById<ImageButton>(R.id.imageButton5)

    }
}