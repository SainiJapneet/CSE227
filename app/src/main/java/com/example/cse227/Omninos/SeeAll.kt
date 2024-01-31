package com.example.cse227.Omninos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import com.example.cse227.Omninos.Adapters.GridAdapter
import com.example.cse227.R

class SeeAll : AppCompatActivity() {
    lateinit var txtSeeAllTitle: TextView
    lateinit var grdView: GridView
    var arrList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all)
        txtSeeAllTitle = findViewById(R.id.txtSeeAllTitle)
        grdView = findViewById(R.id.grdView)

        val title = intent.getStringExtra("title")
        val list = intent.getStringArrayListExtra("list")

        for(i in list!!){
            arrList.add(i)
        }
        txtSeeAllTitle.text = title
        grdView.numColumns= 2
        grdView.adapter =GridAdapter(arrList)
    }
}