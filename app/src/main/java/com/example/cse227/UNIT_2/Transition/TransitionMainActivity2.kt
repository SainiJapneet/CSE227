package com.example.cse227.UNIT_2.Transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.cse227.R

class TransitionMainActivity2 : AppCompatActivity() {
    lateinit var imgTransition: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_main2)
        imgTransition = findViewById(R.id.imgTransition)
        val fade = Fade()
        window.enterTransition = fade
        window.exitTransition = fade
        imgTransition.setOnClickListener {
            val intent = Intent(this,TransitionActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgTransition,ViewCompat.getTransitionName(imgTransition)!!)
            startActivity(intent,options.toBundle())
        }
    }
}