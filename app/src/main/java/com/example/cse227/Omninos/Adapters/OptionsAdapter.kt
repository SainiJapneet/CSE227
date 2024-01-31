package com.example.cse227.Omninos.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R
import com.example.cse227.UNIT_1.CRUD.EmpAdapter

class OptionsAdapter(var list: ArrayList<String>):RecyclerView.Adapter<OptionsAdapter.DataHolder>() {


    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtOptions: TextView = itemView.findViewById(R.id.txtOptions)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.custom_options_rcy,parent,false)
        return DataHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = list[position]
        holder.txtOptions.text = data
    }
}