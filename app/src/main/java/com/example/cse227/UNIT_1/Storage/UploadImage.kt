package com.example.cse227.UNIT_1.Storage

import android.app.ProgressDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cse227.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class UploadImage : AppCompatActivity() {
    lateinit var btnPick: Button
    lateinit var btnUpload: Button
    lateinit var imgUpload: ImageView
    var fileUri: Uri? = null
    lateinit var getImage: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        btnPick = findViewById(R.id.btnPick)
        btnUpload = findViewById(R.id.btnUpload)
        imgUpload = findViewById(R.id.imgUpload)
        getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                if(it != null){
                    fileUri = it
                }
                imgUpload.setImageURI(it)
            })
        btnPick.setOnClickListener {
            getImage.launch("image/*")
        }
        btnUpload.setOnClickListener {
            uploadImage()
        }
    }

    fun uploadImage(){
        if(fileUri != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading")
            progressDialog.setMessage("Uploading image...")
            progressDialog.show()

            val ref: StorageReference = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString())
            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this,"Image uploaded",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(this,"Failed to upload" + it.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
}