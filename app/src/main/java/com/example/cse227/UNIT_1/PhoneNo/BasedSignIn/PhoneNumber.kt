package com.example.cse227.UNIT_1.PhoneNo.BasedSignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

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
        edtPhone1 = findViewById(R.id.edtPhone)

        btnOTP.setOnClickListener {
            login()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                startActivity(Intent(applicationContext,PhoneNumber::class.java))
                finish()
                Log.d("OTP","onVerificationCompleted Success")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("OTP","onVerificationCompleted Failed : $p0")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                Log.d("OTP","onCodeSent : $p0")
                storedVerificationId = p0
                resendToken = p1

                val intent = Intent(applicationContext,OTP::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                startActivity(intent)
                finish()
            }
        }

    }
    private fun login(){
        number = edtPhone1.text.trim().toString()

        if(number.isNotEmpty()){
            number ="+91$number"
            sendVerificationCode(number)
        }else{
            Toast.makeText(this,"Enter mobile number",Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerificationCode(number: String){
        val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(number).setTimeout(60L,TimeUnit.SECONDS).setActivity(this).setCallbacks(callbacks).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("OTP","OTP auth initiated")
    }
}