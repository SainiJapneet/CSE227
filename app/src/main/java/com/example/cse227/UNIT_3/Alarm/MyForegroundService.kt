package com.example.cse227.UNIT_3.Alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.cse227.R

class MyForegroundService: Service() {
    private var channelId = "NOTIFICATION_CHANNEL"
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarm_sound)
        mediaPlayer.isLooping = true
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val alarmTimeInMillis = intent?.getLongExtra("alarmTime", 0L)
            Log.d("Foreground Service","alarm duration : ${alarmTimeInMillis}")
            val action = intent!!.action
            if(alarmTimeInMillis!! > 0L){
                when (action) {
                    "ACTION_START" -> {
                        val notificationChannel = NotificationChannel(
                            channelId,
                            "Alarm Channel",
                            NotificationManager.IMPORTANCE_HIGH
                        )
                        val notificationManager = getSystemService(NotificationManager::class.java)
                        notificationManager.createNotificationChannel(notificationChannel)
                        val notification = NotificationCompat.Builder(this, channelId)
                            .setContentTitle("Alarm")
                            .setContentText("Alarm is playing")
                            .setSmallIcon(R.drawable.add_alarm)
                            .build()
                        val notificationId = 1
                        startForeground(notificationId,notification)
                        Handler().postDelayed({
                            triggerAlarm(this)
                        }, alarmTimeInMillis!!)
                    }
                    "ACTION_STOP" -> {
                        stopAlarm()
                    }
                    "ACTION_SNOOZE" ->{
                        val snoozeTime = intent.getLongExtra("snoozetime",0L)
                        snoozeAlarm(snoozeTime)
                    }
                    else -> {
                        stopSelf()
                    }
                }
            }else{
                Toast.makeText(this,"Negative alarmTime: ${alarmTimeInMillis}",Toast.LENGTH_SHORT).show()
            }

        }
        return START_STICKY
    }

    private fun triggerAlarm(context: Context) {
        mediaPlayer.start()
        val intent = Intent(context, AlarmOverlayActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun stopAlarm() {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
            stopForeground(true)
            stopSelf()
        } catch (e: Exception) {
            Log.e("Foreground Service", "Failed to stop alarm: ${e.message}")
        }
    }

    private fun snoozeAlarm(snoozeDurationMillis: Long) {
        if(mediaPlayer.isPlaying){
            mediaPlayer?.stop()
        }
        mediaPlayer?.release()

        val snoozeIntent = Intent(this, MyForegroundService::class.java)
        snoozeIntent.action = "ACTION_START"
        snoozeIntent.putExtra("snoozeTime", snoozeDurationMillis)
        startService(snoozeIntent)

        stopForeground(true)
        stopSelf()
    }
}