package com.example.cse227.UNIT_3.Alarm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.cse227.R
import com.example.cse227.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlarmBinding
    var arrList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkOverlayPermission()


        binding.floatingActionButton.setOnClickListener{
            addAlarmActivity()
        }
    }
    fun addAlarmActivity(){
        val intent = Intent(this, AlarmSchedulerTrigger::class.java)
        startActivity(intent)
    }

    fun checkOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 101)
        } else {
            Toast.makeText(this, "Screen Overlay permission not granted",Toast.LENGTH_SHORT).show()
        }
    }
}