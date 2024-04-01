package com.anangkur.synrgychapter3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataParcelable(
    val string: String,
    val integer: Int,
): Parcelable