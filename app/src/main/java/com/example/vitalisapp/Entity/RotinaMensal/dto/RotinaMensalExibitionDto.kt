package com.example.vitalisapp.Entity.RotinaMensal.dto

import com.google.gson.annotations.SerializedName

data class RotinaMensalExibitionDto (
    @SerializedName("id") val idRotinaMensal: Int,
    val mes: Int,
    val ano: Int,
)