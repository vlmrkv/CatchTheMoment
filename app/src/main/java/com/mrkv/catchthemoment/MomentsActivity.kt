package com.mrkv.catchthemoment

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MomentsActivity(private val dataList: MutableList<MomentsData>) : AppCompatActivity() {

    private val MOMENTS_PREFERENCES = "moment card"
    private val MOMENT_PREF_INDICATOR = "moment pref indicator"
    private val MOMENT_PREF_IMAGE = "moment pref image"
    private val MOMENT_PREF_TEXT = "moment pref text"
    private val MOMENT_PREF_DATE = "moment pref date"
    private lateinit var momentCard: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moments)

        // Get mutable list from intent
        val dataList: MutableList<MomentsData> = intent.getParcelableArrayListExtra<MomentsData>("dataList") as MutableList<MomentsData>
        val momentsAdapter = MomentsAdapter(dataList)
        val recyclerView = findViewById<RecyclerView>(R.id.moments_recycler_view)
        recyclerView.adapter = momentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        saveMoments()
    }

    private fun saveMoments() {
        momentCard = getSharedPreferences(MOMENTS_PREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = momentCard.edit()
//        editor.putString(MOMENT_PREF_TEXT, intent.getStringExtra("momentText").toString())
//        editor.putString(MOMENT_PREF_DATE, intent.getStringExtra("dateOfCreate").toString())
        editor.apply()
    }
}