package com.example.cse227.UNIT_3.Alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action == "ALARM_TRIGGER"){
            val intent = Intent(p0, OverlayActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.startActivity(intent)
            playSound(p0!!)
        }
    }

    fun playSound(context: Context){
        val ringtoneUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if(ringtoneUri != null){
            val ringtone: Ringtone = RingtoneManager.getRingtone(context, ringtoneUri)
            ringtone.play()
            Toast.makeText(context,"Alarm triggered",Toast.LENGTH_SHORT).show()
        }
    }
}