package com.mrkv.catchthemoment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var momentDescription: EditText
    private lateinit var addButton: ImageButton
    private val galleryIntent: Intent = Intent()
    private val cameraIntent: Intent = Intent("android.media.action.IMAGE_CAPTURE")
    private val pickIntent: Intent = Intent()

    // take URI of picked image and set image to ImageView
    private val pickPhoto = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { imageUri ->
        if (imageUri != null) {
            imageView.setImageURI(imageUri)
            galleryIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        } else {
            Log.d("PhotoPicker", "No photo selected")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        momentDescription = findViewById(R.id.editMomentText)
        addButton = findViewById(R.id.imageButton)

        imageView.setOnClickListener {
            pickPhoto.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            startActivity(Intent.createChooser())
        }
    }
}