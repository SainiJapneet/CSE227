package com.example.cse227.UNIT_3.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cse227.R
import com.example.cse227.databinding.ActivityOverlayBinding

class OverlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityOverlayBinding
    val snoozeTime = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlay)

        binding.btnStopAlarm.setOnClickListener{
            stopAlarm()
        }
        binding.btnSnoozeAlarm.text = "snooze alarm(+${snoozeTime} min)"
        binding.btnSnoozeAlarm.setOnClickListener {
            snoozeAlarm(snoozeTime)
        }
    }

    fun stopAlarm(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)

        Toast.makeText(this, "Alarm stopped",Toast.LENGTH_SHORT).show()
        finish()
    }

    fun snoozeAlarm(minute : Int){
        val snoozeDuration = minute*60*1000

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent , 0)
        val currentTime = System.currentTimeMillis()
        val snoozeTime = currentTime + snoozeDuration
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent)
        Toast.makeText(this, "Alarm snoozed by ${snoozeTime} min",Toast.LENGTH_SHORT).show()
        finish()
    }
}