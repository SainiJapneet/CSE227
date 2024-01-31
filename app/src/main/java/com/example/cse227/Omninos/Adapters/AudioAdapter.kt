package com.example.cse227.Omninos.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R

class AudioAdapter(var list: ArrayList<String>): RecyclerView.Adapter<AudioAdapter.DataHolder>() {
    class DataHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var txtAudioName: TextView = itemView.findViewById(R.id.txtAudioName)
        var imageView2: ImageView = itemView.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.custom_audio,parent,false)
        return DataHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = list[position]
        holder.txtAudioName.text = data
        holder.imageView2.setImageResource(R.drawable.audio)
    }

}