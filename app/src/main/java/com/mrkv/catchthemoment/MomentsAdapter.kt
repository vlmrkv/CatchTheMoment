package com.mrkv.catchthemoment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MomentsAdapter(private val dataList: List<MomentsData>) : RecyclerView.Adapter<MomentsAdapter.MomentsViewHolder>() {

    class MomentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val indicator: ImageView = itemView.findViewById(R.id.indicate)
        val momentImage: ImageView = itemView.findViewById(R.id.moment_image)
        val momentText: TextView = itemView.findViewById(R.id.moment_text)
        val momentDate: TextView = itemView.findViewById(R.id.moment_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.moments_item, parent, false)
        return MomentsViewHolder(itemView)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: MomentsViewHolder, position: Int) {
        val data = dataList[position]
        // attach data to holder
        holder.indicator.setImageResource(data.indicatorResId)
        holder.momentImage.setImageURI(data.momentImageResId)
        holder.momentText.text = data.momentTextResId
        holder.momentDate.text = data.momentDateResId
    }

}