package com.example.cse227.UNIT_1.CRUD

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CrudActivity : AppCompatActivity() {
    lateinit var edtEmpName: EditText
    lateinit var edtEmpAge: EditText
    lateinit var edtEmpSalary: EditText
    lateinit var btnSave: Button
    lateinit var btnRetrieve: Button
    lateinit var rcyView: RecyclerView
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_activty)

        edtEmpName = findViewById(R.id.edtEmpName)
        edtEmpAge = findViewById(R.id.edtEmpAge)
        edtEmpSalary = findViewById(R.id.edtEmpSalary)
        btnSave = findViewById(R.id.btnSave)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        rcyView = findViewById(R.id.rcyView)
        var arrList = ArrayList<EmpDetails>()
        val layoutManager = LinearLayoutManager(this)
        rcyView.layoutManager = layoutManager

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("EmpDetails")

        btnSave.setOnClickListener {
            val name = edtEmpName.text.trim().toString()
            val age = edtEmpAge.text.trim().toString()
            val salary = edtEmpSalary.text.trim().toString()
            val id = databaseReference.push().key!!
            val emp = EmpDetails(name,id,age,salary)
            databaseReference.child(id).setValue(emp).addOnCompleteListener {
                Toast.makeText(this,"data inserted",Toast.LENGTH_SHORT).show()
                edtEmpName.text.clear()
                edtEmpAge.text.clear()
                edtEmpSalary.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this,"data not inserted : $it",Toast.LENGTH_SHORT).show()
            }
        }

        btnRetrieve.setOnClickListener {
            databaseReference?.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrList.clear()

                    for(ep in snapshot.children){
                        val emp = ep.getValue(EmpDetails :: class.java)
                        if(emp != null){
                            arrList.add(emp)
                        }
                    }

                    rcyView.adapter = EmpAdapter(arrList)
                    Log.d("onDataChange","data retrieved successfully")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
        }



    }
}