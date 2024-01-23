package com.example.cse227.UNIT_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseSignIn : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var btnLogIn: Button
    lateinit var edtName1: EditText
    lateinit var edtEmail1: EditText
    lateinit var edtPassword1: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_sign_in)
        auth = FirebaseAuth.getInstance()

        btnLogIn = findViewById(R.id.btnLogIn)
        edtName1 = findViewById(R.id.edtName1)
        edtEmail1 = findViewById(R.id.edtEmail1)
        edtPassword1 = findViewById(R.id.edtPassword1)

        btnLogIn.setOnClickListener {
            var name = edtName1.text.trim().toString()
            var user = edtEmail1.text.toString()
            var pass = edtPassword1.text.toString()
            login(user,pass,it)
            val intent = Intent(this,UserHome::class.java)
            intent.putExtra("name",name)
            intent.putExtra("email",user)
            startActivity(intent)
        }
    }

    fun login(email: String,pass: String,view: View){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Error " + it.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }
}