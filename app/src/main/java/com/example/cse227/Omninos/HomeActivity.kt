package com.example.cse227.Omninos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cse227.Omninos.Adapters.AudioAdapter
import com.example.cse227.Omninos.Adapters.FeaturedAdapter
import com.example.cse227.Omninos.Adapters.OptionsAdapter
import com.example.cse227.R
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {
    lateinit var txtFeatureAll: TextView
    lateinit var txtAudioAll: TextView
    lateinit var txtPopularAll: TextView
    lateinit var featuredRecycler: RecyclerView
    lateinit var optionsRecycler: RecyclerView
    lateinit var audioRecycler: RecyclerView
    lateinit var popularRecycler: RecyclerView
    var featureList = ArrayList<String>()
    var audioList = ArrayList<String>()
    var popularList = ArrayList<String>()
    var optionList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        txtFeatureAll = findViewById(R.id.txtFeaturedAll)
        txtAudioAll = findViewById(R.id.txtAudioAll)
        txtPopularAll = findViewById(R.id.txtPopularAll)
        featuredRecycler = findViewById(R.id.FeaturedRecycler)
        optionsRecycler = findViewById(R.id.OptionsRecycler)
        audioRecycler = findViewById(R.id.AudioRecycler)
        popularRecycler = findViewById(R.id.PopularRecycler)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.HORIZONTAL

        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = RecyclerView.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(this)
        layoutManager2.orientation = RecyclerView.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(this)
        layoutManager3.orientation = RecyclerView.HORIZONTAL


        featureList.add("International Concert")
        featureList.add("HackQuest")
        featureList.add("CTF")
        featureList.add("Bug Bounty")

        audioList.add("Grand Avenue 1")
        audioList.add("Grand Avenue 2")
        audioList.add("Grand Avenue 3")

        popularList.add("Dummy Data 1")
        popularList.add("Dummy Data 2")
        popularList.add("Dummy Data 3")

        optionList.add("All")
        optionList.add("Live")
        optionList.add("Popular")
        optionList.add("Upcoming")

        featuredRecycler.adapter = FeaturedAdapter(featureList)
        optionsRecycler.adapter = OptionsAdapter(optionList)
        audioRecycler.adapter = AudioAdapter(audioList)
        popularRecycler.adapter = AudioAdapter(popularList)

        featuredRecycler.layoutManager = layoutManager
        optionsRecycler.layoutManager = layoutManager1
        audioRecycler.layoutManager = layoutManager2
        popularRecycler.layoutManager = layoutManager3

        txtFeatureAll.setOnClickListener {
            val intent = Intent(this,SeeAll::class.java)
            intent.putExtra("title","Featured")
            intent.putExtra("list",featureList)
            startActivity(intent)
        }

        txtAudioAll.setOnClickListener {
            val intent = Intent(this,SeeAll::class.java)
            intent.putExtra("title","Audio Live")
            intent.putExtra("list",audioList)
            startActivity(intent)
        }

        txtPopularAll.setOnClickListener {
            val intent = Intent(this,SeeAll::class.java)
            intent.putExtra("title","Popular")
            intent.putExtra("list",popularList)
            startActivity(intent)
        }

    }
}