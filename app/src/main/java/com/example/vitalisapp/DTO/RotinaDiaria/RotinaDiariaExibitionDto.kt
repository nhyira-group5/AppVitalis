package com.example.vitalisapp.DTO.RotinaDiaria

import com.google.gson.annotations.SerializedName

data class RotinaDiariaExibitionDto(
    val idRotinaDiaria: Int,
    @SerializedName("rotinaSemanalId") val rotinaSemanal: RotinaSemanalDto,
    @SerializedName("refeicaoDiariaDtos") val refeicaoDiaria: MutableList<RefeicaoDiariaDto>,
    val dia: Int,
    val concluido: Int,
    val totalExercicios: Int,
    val totalExerciciosConcluidos: Int
)

data class RotinaSemanalDto(
    val id: Int,
    val concluido: Int
)

data class RefeicaoDiariaDto(
    val idRotinaDiaria: Int,        // DEVERIA SER idRefeicaoDiaria
    val concluido: Int
)