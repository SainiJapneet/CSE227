package com.example.cse227.UNIT_1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class FirebaseActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var db: DatabaseReference
    lateinit var userDetail: UserDetail

    lateinit var btnSignUp: Button
    lateinit var edtName: EditText
    lateinit var edtContact: EditText
    lateinit var edtAddress: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtPasswordRepeat: EditText
    lateinit var txtAccExists: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        auth = FirebaseAuth.getInstance()
        userDetail = UserDetail()
        firebaseDatabase = Firebase.database
        db= firebaseDatabase.getReference("User Details")
        //db.child("K202").child("CSE227").setValue("UNIT_1")

        btnSignUp = findViewById(R.id.btnSignUp)
        edtName = findViewById(R.id.edtName)
        edtContact = findViewById(R.id.edtContact)
        edtAddress = findViewById(R.id.edtAddress)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtPasswordRepeat = findViewById(R.id.edtPasswordRepeat)
        txtAccExists = findViewById(R.id.txtAccExists)

        btnSignUp.setOnClickListener {

            if(edtPassword.text.trim().toString() != null && edtPasswordRepeat.text.trim().toString() != null){
                val name = edtName.text.trim().toString()
                val contact = edtContact.text.trim().toString()
                val address = edtAddress.text.trim().toString()
                var user = edtEmail.text.trim().toString()
                var pass = edtPassword.text.trim().toString()
                var passRepeat = edtPasswordRepeat.text.toString()

                if (pass.equals(passRepeat)){

                    //Verification link
                    auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this,"Email Sent",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"Email not sent : ${it.exception}",Toast.LENGTH_SHORT).show()
                        }
                    }

                    //User Creation
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            addDataToFirebase(name,contact,address)
                        }else {
                            Toast.makeText(this, "Error " + it.exception, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else if(user == null || pass == null || passRepeat == null){
                    edtEmail.error = "Can't be empty"
                    edtPassword.error = "Can't be empty"
                    edtPasswordRepeat.error = "Can't be empty"
                }
                else{
                    edtPassword.error = "Password mismatch"
                    edtPasswordRepeat.error = "Password mismatch"
                    Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
                }
            }else{
                edtPassword.error = "Error"
                edtPasswordRepeat.error = "Error"
            }
        }
        txtAccExists.setOnClickListener {
            val intent = Intent(this,FirebaseSignIn::class.java)
            startActivity(intent)
        }

    }

    private fun addDataToFirebase(n: String, c: String, a: String){
        userDetail.setName(n)
        userDetail.setContact(c)
        userDetail.setAddress(a)
        val positionListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                db.child(n).setValue(userDetail)
                Toast.makeText(applicationContext,"data added",Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG,"positionListener:onCancelled",error.toException())
                Toast.makeText(applicationContext,"failed to add data",Toast.LENGTH_SHORT).show()
            }
        }
        db.addValueEventListener(positionListener)
    }


}