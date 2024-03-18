package com.example.cse227.UNIT_3.Alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cse227.R
import com.example.cse227.databinding.ActivityAlarmBinding
import com.example.cse227.databinding.ActivityAlarmTriggerBinding

class AlarmActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlarmBinding
    var arrList = ArrayList<AlarmModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AlarmAdapter(this,R.layout.custom_view,arrList)
        binding.listView.adapter = adapter


        binding.floatingActionButton.setOnClickListener{
            addAlarmActivity()
        }
    }
    fun addAlarmActivity(){
        val intent = Intent(this, AlarmTrigger::class.java)
        startActivity(intent)
    }
}