package com.example.cse227.UNIT_3.Alarm

import android.app.ActivityManager
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.cse227.R
import com.example.cse227.databinding.ActivityOverlayBinding

class AlarmOverlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityOverlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )


        binding = ActivityOverlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStopAlarm.setOnClickListener {
            stopAlarm(this)
        }
        binding.btnSnoozeAlarm.text = "Snooze Alarm(+2 min)"
        binding.btnSnoozeAlarm.setOnClickListener {
            snoozeAlarm(this,2)
        }
    }

    private fun stopAlarm(context: Context){
        val serviceIntent = Intent(context, MyForegroundService::class.java)
        serviceIntent.action = "ACTION_STOP"
        startService(serviceIntent)
        Toast.makeText(context,"Alarm stopped",Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun snoozeAlarm(context: Context, time: Int){

        val snoozeDurationMillis = (time * 60 * 1000).toLong()
        val snoozeIntent = Intent(context, MyForegroundService::class.java)
        snoozeIntent.action = "ACTION_SNOOZE"
        snoozeIntent.putExtra("snoozeTime", snoozeDurationMillis)
        startService(snoozeIntent)
        Toast.makeText(context,"Alarm snoozed",Toast.LENGTH_SHORT).show()
        finish()
    }
}