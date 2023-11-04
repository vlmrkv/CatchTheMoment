package com.mrkv.catchthemoment

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MomentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moments)

        val imageView = intent.data
        val momentText = intent.dataString
        val dateText = intent.dataString

        val dataList: List<MomentsData> = listOf(
            MomentsData(R.drawable.yellow_indicator, imageView, momentText, dateText)
        )
        val momentsAdapter = MomentsAdapter(dataList)
        val recyclerView = findViewById<RecyclerView>(R.id.moments_recycler_view)
        recyclerView.adapter = momentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}