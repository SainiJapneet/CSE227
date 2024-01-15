package com.example.cse227.UNIT_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cse227.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth

    lateinit var btnSignUp: Button
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtPasswordRepeat: EditText
    lateinit var txtAccExists: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        auth = FirebaseAuth.getInstance()

        btnSignUp = findViewById(R.id.btnSignUp)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtPasswordRepeat = findViewById(R.id.edtPasswordRepeat)
        txtAccExists = findViewById(R.id.txtAccExists)


        btnSignUp.setOnClickListener {
            var user = edtEmail.text.toString()
            var pass = edtPassword.text.toString()
            var passRepeat = edtPasswordRepeat.text.toString()
            if (pass.equals(passRepeat)){
                auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Email Sent",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Email not sent : ${it.exception}",Toast.LENGTH_SHORT).show()
                    }
                }
                auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        //Toast.makeText(this,"Signup successful",Toast.LENGTH_SHORT).show()
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

            txtAccExists.setOnClickListener {
                val intent = Intent(this,FirebaseSignIn::class.java)
                startActivity(intent)
            }

        }



    }


}