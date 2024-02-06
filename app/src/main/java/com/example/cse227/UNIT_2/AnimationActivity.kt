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
    lateinit var btnFadeIn: Button
    lateinit var btnFadeOut: Button
    lateinit var btnRotate: Button
    lateinit var btnBounce: Button
    lateinit var btnSlideIn: Button
    lateinit var btnSlideDown: Button
    lateinit var btnFlip: Button
    lateinit var btnZoomIn: Button
    lateinit var btnZoomOut: Button
    lateinit var animBlink: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        imgViewAnim = findViewById(R.id.imgViewAnim)
        btnFadeIn = findViewById(R.id.btnFadeIn)
        btnFadeOut = findViewById(R.id.btnFadeOut)
        btnBounce = findViewById(R.id.btnBounce)
        btnRotate = findViewById(R.id.btnRotate)
        btnFlip = findViewById(R.id.btnFlip)
        btnSlideIn = findViewById(R.id.btnSlideUp)
        btnSlideDown = findViewById(R.id.btnSlideDown)
        btnZoomIn = findViewById(R.id.btnZoomIn)
        btnZoomOut = findViewById(R.id.btnZoomOut)

        imgViewAnim.setImageResource(R.drawable.profile)
        /*
        val blink = AlphaAnimation(0.0f,1.0f)//transparent = 0,0 & opaque = 1,1
        blink.duration = 50
        blink.repeatMode- Animation.REVERSE
        blink.repeatCount = Animation.INFINITE
        blink.startOffset = 20
        imgViewAnim.startAnimation(blink)

         */

        btnFadeIn.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnFadeOut.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_out_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnRotate.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.roatate_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnBounce.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.bounce_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnFlip.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.flip_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnSlideIn.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_up_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnSlideDown.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_down_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnZoomIn.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.zoom_in_anim)
            imgViewAnim.startAnimation(animBlink)
        }
        btnZoomOut.setOnClickListener {
            animBlink = AnimationUtils.loadAnimation(applicationContext,R.anim.zoom_out_anim)
            imgViewAnim.startAnimation(animBlink)
        }


    }
}