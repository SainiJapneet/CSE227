package com.example.cse227.UNIT_2.Transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cse227.R

class TransitionMainActivity : AppCompatActivity() {
    lateinit var btnTransition: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_main)
        btnTransition = findViewById(R.id.btnTransition)
        btnTransition.setOnClickListener {
            var intent = Intent(this,TransitionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim)
        }

    }
}