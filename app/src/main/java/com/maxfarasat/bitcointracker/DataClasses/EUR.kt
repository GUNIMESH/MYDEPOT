package com.maxfarasat.bitcointracker.DataClasses
import com.google.gson.annotations.SerializedName

data class EUR(
    val code: String,
    val description: String,
    val rate: String,
    @SerializedName("rate_float")
    val rateFloat: Double,
    val symbol: String
)