package com.example.cse227.UNIT_1

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.TextView
import com.example.cse227.R
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class UserHome : AppCompatActivity() {
    lateinit var txtName: TextView
    lateinit var txtEmail: TextView
    lateinit var txtContact: TextView
    lateinit var txtAddress: TextView

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtContact = findViewById(R.id.txtContact)
        txtAddress = findViewById(R.id.txtAddress)

        firebaseDatabase = FirebaseDatabase.getInstance()

        var email = intent.getStringExtra("email")
        txtEmail.text = email
        var name = intent.getStringExtra("name")!!
        databaseReference = firebaseDatabase.reference.child("User Details").child(name)




        databaseReference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var arr = ArrayList<String>()
               for(user in snapshot.children){
                   val usr = user.getValue().toString()
                   arr.add(usr)
               }
                txtName.text = arr[2]
                txtContact.text = arr[1]
                txtAddress.text = arr[0]
                arr.clear()
                Log.d("onDataChange","data retrieved successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


    }
}