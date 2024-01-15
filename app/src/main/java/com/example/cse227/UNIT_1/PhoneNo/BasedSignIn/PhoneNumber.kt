package com.example.cse227.UNIT_1.PhoneNo.BasedSignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.cse227.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class PhoneNumber : AppCompatActivity() {

    var number: String = ""
    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    lateinit var edtPhone1: EditText
    lateinit var btnOTP: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)

        auth = FirebaseAuth.getInstance()
        btnOTP = findViewById(R.id.btnOTP)

        btnOTP.setOnClickListener {
            login()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                startActivity(Intent(applicationContext,OTP::class.java))
                finish()
                Log.d("OTP","onVerificationCompleted Success")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("OTP","onVerificationCompleted Failed : $p0")
            }
        }
    }
    fun login(){

    }
}