package com.example.cse227.Omninos.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cse227.R

class GridAdapter(var list: ArrayList<String>?): BaseAdapter() {
    override fun getCount(): Int {
        return list!!.size
    }

    override fun getItem(p0: Int): Any {
        return list!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(p2!!.context).inflate(R.layout.custom_audio,p2,false)
        val txtTitle = inflater.findViewById<TextView>(R.id.txtAudioName)
        val imageView2 = inflater.findViewById<ImageView>(R.id.imageView2)
        imageView2.setImageResource(R.drawable.audio)
        txtTitle.text = list!![p0]
        return inflater
    }
}