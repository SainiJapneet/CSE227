package com.example.cse227.UNIT_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.example.cse227.R

class AnimationActivity : AppCompatActivity() {
    lateinit var imgViewAnim: ImageView
    lateinit var btnAnim: Button
    lateinit var animBlink: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        imgViewAnim = findViewById(R.id.imgViewAnim)
        btnAnim = findViewById(R.id.btnAnim)

        imgViewAnim.setImageResource(R.drawable.profile)
        /*
        val blink = AlphaAnimation(0.0f,1.0f)//transparent = 0,0 & opaque = 1,1
        blink.duration = 50
        blink.repeatMode- Animation.REVERSE
        blink.repeatCount = Animation.INFINITE
        blink.startOffset = 20
        imgViewAnim.startAnimation(blink)

         */

        btnAnim.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_anim)
            imgViewAnim.startAnimation(animBlink)
        }

    }
}