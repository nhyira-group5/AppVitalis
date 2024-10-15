package com.example.vitalisapp.DTO.RotinaSemanal

import com.google.gson.annotations.SerializedName

data class RotinaSemanalExibitionDto (
    @SerializedName("id") val idRotinaSemanal: Int,
    @SerializedName("rotinaMensalId") val rotinaMensal: RotinaMensalDto,
    @SerializedName("rotinaDiariaDtos") val rotinaDiaria: MutableList<RotinaDiariaDto>,
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