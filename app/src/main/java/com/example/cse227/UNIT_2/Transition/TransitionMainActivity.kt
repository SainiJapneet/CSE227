package com.example.cse227.UNIT_2.Transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cse227.R
import com.example.cse227.UNIT_2.Canvas.CanvasActivity

class TransitionMainActivity : AppCompatActivity() {
    lateinit var btnTransition: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_main)
        btnTransition = findViewById(R.id.btnTransition)
        btnTransition.setOnClickListener {
            var intent = Intent(this,CanvasActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_anim,R.anim.zoom_out_anim)
        }

    }
}