package com.example.vitalisapp.Entity.Alimento

import com.google.gson.annotations.SerializedName

data class Alimento (
    @SerializedName("id_alimento") var idAlimento: Int,
    var nome: String,
    var carboidrato: Double,
    var proteina: Double,
    var gordura: Double
)