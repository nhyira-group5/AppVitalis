package com.example.vitalisapp.Entity.RotinaSemanal.dto

import com.google.gson.annotations.SerializedName

data class RotinaSemanalExibitionDto (
    @SerializedName("id") val idRotinaSemanal: Int,
    @SerializedName("rotinaMensalId") val rotinaMensal: RotinaMensalDto,
    @SerializedName("rotinaDiariaDtos") val rotinaDiaria: RotinaDiariaDto,
    val numSemana: Int,
    val concluido: Int
)

data class RotinaMensalDto(
    val id: Int,
    val mes: Int,
    val ano: Int
)

data class RotinaDiariaDto(
    val idRotinaDiaria: Int,
    val concluido: Int
)