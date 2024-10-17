package com.example.vitalisapp.DTO.Alimento

import com.google.gson.annotations.SerializedName

data class AlimentoExibitionDto (
    val idAlimento: Int,
    val nome: String,
    val carboidrato: Double,
    val proteina: Double,
    val gordura: Double,
    @SerializedName("midia") val midias: MutableList<MidiaDto>
)

data class MidiaDto(
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)