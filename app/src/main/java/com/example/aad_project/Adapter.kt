package com.example.aad_project

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val data: List<Message>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val message=itemView.findViewById<TextView>(R.id.robot_msg_id)
        val user=itemView.findViewById<ImageView>(R.id.user_icon)
        val bot=itemView.findViewById<ImageView>(R.id.bot_icon)
        val layout=itemView.findViewById<androidx.cardview.widget.CardView>(R.id.card_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.bot_msg_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val current=data[position]
        holder.message.text=current.message
        val layoutParams=holder.layout.layoutParams
        val paramsType=layoutParams as LinearLayout.LayoutParams
        if(current.isMe){
            holder.user.visibility=View.VISIBLE
            holder.bot.visibility=View.GONE
            paramsType.gravity= Gravity.END
        }
        else{
            holder.user.visibility=View.GONE
            holder.bot.visibility=View.VISIBLE
            paramsType.gravity= Gravity.START
            holder.layout.setCardBackgroundColor(0xFFFFFFFF.toInt())
        }
    }
}