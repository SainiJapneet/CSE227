package com.example.cse227.UNIT_3.Alarm

import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cse227.databinding.ActivityAlarmTriggerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmTrigger : AppCompatActivity() {
    lateinit var binding: ActivityAlarmTriggerBinding
    var hour = 0
    var minute = 0
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
        hour = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            val time = "${i}:${i2}"
            binding.edtAlarm.setText(time)
        },hour,minute,true)
        timePickerDialog.show()
    }
    fun setAlarm(){
        if(!isEmpty()){
            val calendar = Calendar.getInstance()
            val currentTime = calendar.timeInMillis
            calendar.set(Calendar.HOUR_OF_DAY,hour)
            calendar.set(Calendar.MINUTE,minute)
            calendar.set(Calendar.SECOND,0)
            val alarmTime = calendar.timeInMillis

            val timeDifference = alarmTime - currentTime
            val component = ComponentName(this, MyJobScheduler::class.java)
            val builder = JobInfo.Builder(1, component)
                .setRequiresCharging(false)
                .setPersisted(true)
                .setMinimumLatency(timeDifference)
                .build()

            val jobScheduler = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val resultCode = jobScheduler.schedule(builder)

            if(resultCode == JobScheduler.RESULT_SUCCESS){
                Toast.makeText(this, "Alarm scheduled", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Failed to schedule alarm", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getNotification(){

    }
}