package com.example.cse227.UNIT_3.Alarm

import android.app.AlarmManager
import android.app.Notification
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
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.cse227.R
import kotlin.properties.Delegates

class MyForegroundService : Service() {
    private val channelId = "NOTIFICATION CHANNEL"
    lateinit var mediaPlayer: MediaPlayer
    var alarmTimeInMillis = 10L

    override fun onCreate() {
        super.onCreate()
        Log.d("MyForegroundService","In onCreate function")
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound)
        alarmTimeInMillis = 0L
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyForegroundService","In onStartCommand function")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alarmTimeInMillis = intent?.getLongExtra("alarmTime", 0L)!!
            val action = intent!!.action

            when (action) {
                "ACTION_START" -> {
                    Log.d("MyForegroundService","In ACTION_START")
                    val notificationId = 201
                    startForeground(notificationId, createNotification())
                    Handler().postDelayed({ triggerAlarm(this) },alarmTimeInMillis!!)
                }
                "ACTION_STOP" -> {
                    Toast.makeText(this,"In ACTION_STOP",Toast.LENGTH_SHORT).show()
                    stopAlarm(this)
                    stopSelf()
                }
                "ACTION_SNOOZE" -> {
                    /*
                    Log.d("MyForegroundService","In ACTION_SNOOZE")
                    val snoozeTime = intent.getLongExtra("snoozeTime",0L)
                    val alarmTime = snoozeTime + System.currentTimeMillis()
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    val notificationId = 201
                    startForeground(notificationId, createNotification())
                    Handler().postDelayed({ triggerAlarm(this) },alarmTime!!)
                     */
                    val snoozeTime = intent.getLongExtra("snoozeTime",0L)
                    snoozeAlarm(this, snoozeTime)

                }
                else -> {
                    stopSelf()
                }
            }
        }
        return START_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    private fun triggerAlarm(context: Context){
        Toast.makeText(context,"Alarm Triggered",Toast.LENGTH_SHORT).show()
        mediaPlayer?.start()
        if(isScreenLocked(context)){
            showOverlay(context)
        }else{
            showNotification(context)
        }
    }

    private fun stopAlarm(context: Context) {
        Log.d("MyForegroundService", "Stop Alarm function called")
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }
        stopService(Intent(this,MyForegroundService::class.java))
        stopForeground(true)
        stopSelf()
    }

    private fun snoozeAlarm(context: Context ,snoozeDurationMillis: Long) {
        Log.d("MyForegroundService", "Snooze alarm function called")

        mediaPlayer.stop()
        mediaPlayer.release()

        val alarmTime = System.currentTimeMillis() + snoozeDurationMillis

        Toast.makeText(context,"Alarm snoozed for ${alarmTime/(60*1000)}",Toast.LENGTH_SHORT).show()
        val snoozeIntent = Intent(context, MyForegroundService::class.java).apply {
            action = "ACTION_START"
            putExtra("alarmTime", alarmTime)
        }
        context.startService(snoozeIntent)
    }

    private fun createNotification(): Notification {
        Log.d("MyForegroundService","Creating a notification")
        val intent = Intent(this, AlarmOverlayActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("Your service is running in the foreground")
            .setSmallIcon(R.drawable.add_alarm)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Close notification when tapped
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        return notificationBuilder.build()
    }

    private fun showOverlay(context: Context){
        val intent = Intent(context, AlarmOverlayActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun isScreenLocked(context: Context): Boolean{
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return !powerManager.isInteractive
    }

    private fun showNotification(context: Context){
        val stopIntent = Intent(context, MyForegroundService::class.java).apply {
            action = "ACTION_STOP"
        }
        val stopPendingIntent = PendingIntent.getService(context,202,stopIntent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        val snoozeIntent = Intent(context, MyForegroundService::class.java).apply {
            action = "ACTION_SNOOZE"
            putExtra("snoozeTime",2*60*1000)
        }
        val snoozePendingIntent = PendingIntent.getService(context,202,snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("ALARM TRIGGERED")
            .setContentText("Alarm is ringing...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(R.drawable.stop,"Stop",stopPendingIntent)
            .addAction(R.drawable.add_alarm,"Snooze",snoozePendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(false)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0,builder.build())
    }
}