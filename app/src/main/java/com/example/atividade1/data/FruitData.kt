package com.example.atividade1.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FruitData(
    val name: String,
    val description: String,
    val image: Uri?
): Parcelable{
    override fun toString(): String {
        return "$name"
    }
}
