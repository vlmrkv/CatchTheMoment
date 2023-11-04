package com.mrkv.catchthemoment

import android.net.Uri

data class MomentsData (
    val indicatorResId: Int,
    val momentImageResId: Uri?,
    val momentTextResId: String?,
    val momentDateResId: String?
)