package com.example.vitalisapp.DTO.RotinaMensal

import com.google.gson.annotations.SerializedName

data class RotinaMensalExibitionDto (
    @SerializedName("id") val idRotinaMensal: Int,
    val mes: Int,
    val ano: Int,
)