package com.mrkv.catchthemoment

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MomentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moments)

        val dataList: List<MomentsData> = listOf(
            MomentsData(R.drawable.yellow_indicator, getImageFromIntent(), getMomentTextFromIntent(), getMomentDateFromIntent())
        )
        val momentsAdapter = MomentsAdapter(dataList)
        val recyclerView = findViewById<RecyclerView>(R.id.moments_recycler_view)
        recyclerView.adapter = momentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getImageFromIntent(): Bitmap? {
        val imageBytes = intent.getByteArrayExtra("image")
        val imageBitmap = imageBytes?.let { BitmapFactory.decodeByteArray(imageBytes, 0, it.size) }
        return imageBitmap
    }

    private fun getMomentTextFromIntent(): String? {
        return intent.getStringExtra("momentText")
    }

    private fun getMomentDateFromIntent(): String? {
        return intent.getStringExtra("dateOfCreate")
    }

    private fun saveMoments() {

    }
}