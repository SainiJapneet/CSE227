package com.example.cse227.UNIT_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword("test@gmail.com","testing").addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Signup successful",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Error " + it.exception, Toast.LENGTH_SHORT).show()
            }
        }

    }
}