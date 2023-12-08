package com.kultura.app.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topeng(
    val name: String,
    val description: String
): Parcelable