package com.example.cse227.UNIT_3.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.widget.Toast

class MyJobScheduler: JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = "ALARM_TRIGGER"
        val pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_IMMUTABLE)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),pendingIntent)
        Toast.makeText(this,"Alarm Set", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

}