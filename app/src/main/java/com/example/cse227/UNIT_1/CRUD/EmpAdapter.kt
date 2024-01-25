package com.example.cse227.UNIT_1.CRUD

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R

class EmpAdapter(var list: List<EmpDetails>):RecyclerView.Adapter<EmpAdapter.DataHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }
    class DataHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var txtId: TextView = itemView.findViewById(R.id.txtId)
        var txtName1: TextView = itemView.findViewById(R.id.txtName1)
        var txtAge: TextView = itemView.findViewById(R.id.txtAge)
        var txtSalary: TextView = itemView.findViewById(R.id.txtSalary)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.emp_adapter,parent,false)
        return DataHolder(inflater, mListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = list[position]
        holder.txtId.text = data.id
        holder.txtName1.text = data.name
        holder.txtAge.text = data.age
        holder.txtSalary.text = data.salary
    }


}