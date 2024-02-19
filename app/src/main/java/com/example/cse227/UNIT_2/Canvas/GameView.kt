package com.example.cse227.UNIT_2.Canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import com.example.cse227.R

class GameView(ctx: Context): View(ctx) {
    var q = 0;
    var x = 0;
    var bg: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.mario_bg)
    var mario1: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.mario)
    var mario2: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.mario2)
    var crow: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.crow2)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rect1 = Rect(0,0,bg.width,bg.height)
        val rect2 = Rect(0,0,canvas.width,canvas.height)

        val rect3 = Rect(0,canvas.height-bg.height+370,mario1.width,canvas.height)
        canvas.drawBitmap(bg,rect1,rect2,null)
        canvas.drawBitmap(mario2,(0+x).toFloat(),(canvas.height-bg.height+480+q).toFloat(),null)
        canvas.drawBitmap(crow,(900-x).toFloat(),(canvas.height-bg.height+920).toFloat(),null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
       if(event?.action == MotionEvent.ACTION_DOWN){
           q -= 800
           x += 20
           invalidate()
       }
        if(event?.action == MotionEvent.ACTION_UP){
            q += 800
            invalidate()
        }
        return true
    }
}