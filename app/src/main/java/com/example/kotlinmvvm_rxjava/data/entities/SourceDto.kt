package com.example.kotlinmvvm_rxjava.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class SourceDto(
    val name: String,
    val id: String
) {
}
