package com.example.multiscreenapp.model

import com.google.gson.annotations.SerializedName

data class Celebrity(
    @SerializedName("name") val name: String,
    @SerializedName("net_worth") val netWorth: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("height") val height: Double,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("occupation") val occupation: ArrayList<String> = arrayListOf(),
    @SerializedName("age") val age: Int,
    @SerializedName("is_alive") val isAlive: Boolean,
)
