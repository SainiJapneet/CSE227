package com.example.cse227.UNIT_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import com.example.cse227.databinding.ActivityText2SpeechBinding
import java.util.Locale

class Text2Speech : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var binding: ActivityText2SpeechBinding
    private var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityText2SpeechBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this,this)
        binding.btnSpeak.isEnabled = false
        binding.btnSpeak.setOnClickListener { speakOut() }

    }

    override fun onInit(status: Int){
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported!")
            }else{
                binding.btnSpeak.isEnabled = true
            }
        }
    }
    private fun speakOut(){
        val text = binding.edtSpeech!!.text.trim().toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}