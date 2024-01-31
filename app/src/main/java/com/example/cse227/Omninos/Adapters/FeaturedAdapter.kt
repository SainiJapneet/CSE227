package com.example.cse227.Omninos.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R
import kotlin.coroutines.coroutineContext

class FeaturedAdapter(var list: ArrayList<String>):RecyclerView.Adapter<FeaturedAdapter.DataHolder>() {
    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtCustomFeaturedTitle: TextView = itemView.findViewById(R.id.txtCustomFeatureTitle)
        var btnFeaturedButton: Button = itemView.findViewById(R.id.btnBook)
        init {
            btnFeaturedButton.setOnClickListener {
                Toast.makeText(itemView.context,"Booking confirmed",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.custom_featured,parent,false)
        return DataHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = list[position]
        holder.txtCustomFeaturedTitle.text = data
    }

}