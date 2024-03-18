package com.example.cse227.UNIT_3.Alarm

import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cse227.databinding.ActivityAlarmTriggerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmTrigger : AppCompatActivity() {
    lateinit var binding: ActivityAlarmTriggerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmTriggerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtAlarm.setOnClickListener {
            showDatePicker()
        }
        binding.btnSetAlarm.setOnClickListener {
            if(!isEmpty()){
                setAlarm()
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
    fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            val time = "${i}:${i2}"
            binding.edtAlarm.setText(time)
        },hour,minute,true)
        timePickerDialog.show()
    }
    fun setAlarm(){
        if(!isEmpty()){
            val time = binding.edtAlarm.text.toString().trim()
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(time)
            val alarmIntent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this,0,alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
    fun getNotification(){

    }
}