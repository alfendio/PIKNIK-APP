package com.example.piknik

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Piknik(
    val name: String,
    val description: String,
    val rate : String,
    val photo: Int

) : Parcelable