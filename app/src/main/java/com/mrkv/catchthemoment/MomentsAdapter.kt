package com.mrkv.catchthemoment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MomentsAdapter(private val dataset: Set<?>) : RecyclerView.Adapter<MomentsAdapter.MomentsViewHolder>() {

    class MomentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val indicator: ImageView = itemView.findViewById(R.id.indicate)
        private val momentImage: ImageView = itemView.findViewById(R.id.moment_image)
        private val momentText: TextView = itemView.findViewById(R.id.moment_text)
        private val momentDate: TextView = itemView.findViewById(R.id.moment_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.moments_item, parent, false)
        return MomentsViewHolder(view)
    }

    override fun getItemCount() = dataset.size()

    override fun onBindViewHolder(holder: MomentsViewHolder, position: Int) {
        holder.itemView
    }

}