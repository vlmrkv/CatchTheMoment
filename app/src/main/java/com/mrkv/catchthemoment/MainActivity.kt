package com.mrkv.catchthemoment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var momentDescription: EditText
    private lateinit var addButton: ImageButton
    private val CHANNEL_ID = "CmService Channel"
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
//            pickPhoto.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//            pickIntent.type = "image/*"
//            val chooseIntent = Intent.createChooser(pickIntent, "Select image")
//            chooseIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent, galleryIntent))
//            if (chooseIntent.equals(cameraIntent)) {
//                imageView.setImageURI(cameraIntent.data)
//            }
//            startActivity(chooseIntent)
        }

        addButton.setOnClickListener {
            attachDataToSend()
        }

        val channel = NotificationChannel(
            CHANNEL_ID,
            "CmService Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val serviceIntent = Intent(this, CmService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun attachDataToSend() {
        val image = imageView.drawable.toBitmap(50, 50, null)
        val momentText = momentDescription.text.toString()
        val dateFormat = SimpleDateFormat("hh:mm:ss\ndd/mm/yyyy", Locale.forLanguageTag("ru-RU"))
        val currentDate = dateFormat.format(Date())
        val dataIntent = Intent(this, MomentsActivity::class.java)
        dataIntent.putExtra("image", image)
        dataIntent.putExtra("momentText", momentText)
        dataIntent.putExtra("dateOfCreate", currentDate)
        startActivity(dataIntent)
    }
}