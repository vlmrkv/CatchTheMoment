package com.mrkv.catchthemoment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MomentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moments)

//        val dataset = ? Какую структуру данных выбрать для хранения разных типов элементов строки RecyclerView
        val momentsAdapter = MomentsAdapter(dataset)
        val recyclerView = findViewById<RecyclerView>(R.id.moments_recycler_view)
        recyclerView.adapter = momentsAdapter
    }
}