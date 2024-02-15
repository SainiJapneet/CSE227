package com.example.cse227.UNIT_2.Canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cse227.R

class CanvasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val obj = GameView(this)

        setContentView(obj)
    }
}