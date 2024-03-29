package com.example.cse227.UNIT_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.cse227.R

class SplashScreen : AppCompatActivity() {
    lateinit var imgSplash: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imgSplash = findViewById(R.id.imgSplash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        imgSplash.setImageResource(R.drawable.img)
        val anim = AnimationUtils.loadAnimation(this,R.anim.together_anim)
        imgSplash.startAnimation(anim)
        Handler().postDelayed({
            val intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }

}