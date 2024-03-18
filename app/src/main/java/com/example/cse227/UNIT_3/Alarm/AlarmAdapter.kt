package com.example.cse227.UNIT_3.Alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cse227.R

class AlarmAdapter(var ctx: Context,var resources: Int ,var list: ArrayList<AlarmModel>): ArrayAdapter<AlarmModel>(ctx, resources, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resources, null)
        val txtAlarmTime: TextView = view.findViewById(R.id.txtAlarmTime)
        txtAlarmTime.text = list[position].alarmTime
        return view
    }
}