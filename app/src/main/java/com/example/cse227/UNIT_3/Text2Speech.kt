package com.example.cse227.UNIT_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.cse227.R
import java.lang.Exception
import java.util.Locale

class Text2Speech : AppCompatActivity() {
    private lateinit var imgMic: ImageView
    private lateinit var txtSpeech: TextView
    private val REQUEST_CODE_SPEECH_INPUT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text2_speech)
        imgMic = findViewById(R.id.imgMic)
        txtSpeech = findViewById(R.id.txtSpeech)

        imgMic.setOnClickListener {
            performListen()
        }
    }
    fun performListen(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak to text")
        }
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)

        }catch (e: Exception){
            Toast.makeText(this,"Error : ${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEECH_INPUT){
            if(resultCode == RESULT_OK && data != null){
                val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                txtSpeech!!.text = result?.get(0)
            }
        }
    }
}