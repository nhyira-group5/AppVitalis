package com.example.vitalisapp.DTO.RotinaMensal

import com.google.gson.annotations.SerializedName

data class RotinaMensalExibitionDto (
    @SerializedName("id") var idRotinaMensal: Int? = null,
    var mes: Int? = null,
    var ano: Int? = null,
)