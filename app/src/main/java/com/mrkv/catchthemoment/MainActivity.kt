package com.mrkv.catchthemoment

import android.app.Activity
import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var momentDescription: EditText
    private lateinit var addButton: ImageButton
    private var dataList: MutableList<MomentsData> = mutableListOf()
    private val SELECT_IMAGE_REQUEST = 0
    private var selectedImage: Uri? = null
    private var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.show()

        // Initialization views
        imageView = findViewById(R.id.imageView)
        momentDescription = findViewById(R.id.editMomentText)
        addButton = findViewById(R.id.imageButton)

        imageView.setOnClickListener {
            chooseImage()
        }

        addButton.setOnClickListener {
            attachDataToSend(dataList)
        }

        val serviceIntent = Intent(this, CmService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedImage = data?.data
            Log.d("MainActivity", "onActivityResult started")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.menu_item_history -> startActivity(Intent(this, MomentsActivity::class.java))
        }
        return true
    }

    private fun chooseImage() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val chooserIntent = Intent.createChooser(pickIntent, "Select Image")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(chooserIntent, SELECT_IMAGE_REQUEST)
//        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)

    }

    private fun attachDataToSend(list: MutableList<MomentsData>) {
        val image = imageView.drawable.toBitmap(50, 50, null)
        val momentText = momentDescription.text.toString()
        val dateFormat = SimpleDateFormat("hh:mm:ss\ndd/mm/yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val moment = MomentsData(null, image, momentText, currentDate)
        dataList.add(moment)
        val dataIntent = Intent(this, MomentsActivity::class.java)
        dataIntent.putExtra("dataList", arrayOf(dataList))
//        dataIntent.putExtra("momentText", momentText)
//        dataIntent.putExtra("dateOfCreate", currentDate)
        startActivity(dataIntent)
    }
}