package com.mrkv.catchthemoment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class MomentsData (
    val indicatorResId: Int?,
    val momentImageResId: Bitmap?,
    val momentTextResId: String?,
    val momentDateResId: String?
): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }
}