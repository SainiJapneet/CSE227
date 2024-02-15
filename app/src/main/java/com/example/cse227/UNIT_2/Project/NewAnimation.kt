package com.example.cse227.UNIT_2.Project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.cse227.R

class NewAnimation : AppCompatActivity() {
    lateinit var imgRoad: ImageView
    lateinit var imgCar: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_animation)
        imgRoad = findViewById(R.id.imgRoad)
        imgCar = findViewById(R.id.imgCar)

        imgRoad.setImageResource(R.drawable.road2)
        imgCar.setImageResource(R.drawable.car)
        var animation = AnimationUtils.loadAnimation(this,R.anim.seq_new_anim)
        imgCar.startAnimation(animation)
    }
}