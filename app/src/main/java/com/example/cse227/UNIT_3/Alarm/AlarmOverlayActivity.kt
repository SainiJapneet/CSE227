package com.example.cse227.UNIT_3.Alarm

import android.app.ActivityManager
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cse227.R
import com.example.cse227.databinding.ActivityOverlayBinding

class AlarmOverlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityOverlayBinding
    val snoozeTime = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStopAlarm.setOnClickListener{
            stopAlarm()
        }
        binding.btnSnoozeAlarm.text = "snooze alarm(+${snoozeTime} min)"
        binding.btnSnoozeAlarm.setOnClickListener {
            snoozeAlarm(snoozeTime)
        }
    }

    private fun stopAlarm() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        serviceIntent.action = "ACTION_STOP"
        stopService(serviceIntent)
        Log.d("AlarmOverlayActivity", "Stop intent sent to service")
        finish()
    }

        private fun snoozeAlarm(time: Int) {
        val snoozeDurationMillis = time * 60 * 1000L
        val snoozeIntent = Intent(this, MyForegroundService::class.java)
        snoozeIntent.action = "ACTION_SNOOZE"
        snoozeIntent.putExtra("snoozeDuration", snoozeDurationMillis)
        startService(snoozeIntent)
        finish()
    }
}