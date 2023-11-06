package com.mrkv.catchthemoment

import android.graphics.Bitmap

data class MomentsData (
    val indicatorResId: Int,
    val momentImageResId: Bitmap?,
    val momentTextResId: String?,
    val momentDateResId: String?
)