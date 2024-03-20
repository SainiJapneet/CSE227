package com.example.cse227.UNIT_3.Alarm

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cse227.databinding.ActivityAlarmTriggerBinding
import java.util.Calendar

class AlarmSchedulerTrigger : AppCompatActivity() {
    lateinit var binding: ActivityAlarmTriggerBinding
    private var hr = 0
    private var min = 0
    private var alarmTimeInMillis = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmTriggerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtAlarm.setOnClickListener {
            showTimePicker()
        }
        binding.btnSetAlarm.setOnClickListener {
            if(!isEmpty()){
                scheduleAlarm()
                getNotification()
            }
        }
    }

    fun isEmpty():Boolean{
        var emp = false
        if(binding.edtAlarm.text.trim().toString().isEmpty()){
            emp = true
        }
        return emp
    }
    fun showTimePicker(){
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            val time = "${i}:${i2}"
            hr = i
            min = i2
            binding.edtAlarm.setText(time)
        },hour,minute,true)
        timePickerDialog.show()
    }
    fun scheduleAlarm(){
        if(!isEmpty()){
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY,hr)
            calendar.set(Calendar.MINUTE,min)
            calendar.set(Calendar.SECOND, 0)
            if(calendar.timeInMillis > System.currentTimeMillis()) {
                alarmTimeInMillis = calendar.timeInMillis - System.currentTimeMillis()
            } else {
                Toast.makeText(this, "hr : $hr, min : $min", Toast.LENGTH_SHORT).show()
            }
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            serviceIntent.putExtra("alarmTime",alarmTimeInMillis)
            serviceIntent.action = "ACTION_START"
            ContextCompat.startForegroundService(this, serviceIntent)
        }
    }
    fun getNotification(){

    }
}