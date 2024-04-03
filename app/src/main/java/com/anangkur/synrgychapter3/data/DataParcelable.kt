package com.anangkur.synrgychapter3.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataParcelable(
    val string: String,
    val integer: Int,
): Parcelable