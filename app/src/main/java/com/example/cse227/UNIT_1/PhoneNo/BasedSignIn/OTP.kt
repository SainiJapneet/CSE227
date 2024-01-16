package com.example.cse227.UNIT_1.PhoneNo.BasedSignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTP : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var edtOTP: EditText
    lateinit var btnVerify: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        auth = FirebaseAuth.getInstance()
        edtOTP = findViewById(R.id.edtOTP)
        btnVerify = findViewById(R.id.btnVerify)

        val storedVerificationId = intent.getStringExtra("storedVerificationId")
        btnVerify.setOnClickListener {
            val otp = edtOTP.text.trim().toString()
            if(otp.isNotEmpty()){
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(),otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }else{
                if(task.exception is FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}