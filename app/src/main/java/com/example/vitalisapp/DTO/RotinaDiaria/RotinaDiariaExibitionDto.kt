package com.example.vitalisapp.DTO.RotinaDiaria

import com.google.gson.annotations.SerializedName

data class RotinaDiariaExibitionDto(
    var idRotinaDiaria: Int? = null,
    @SerializedName("rotinaSemanalId") var rotinaSemanal: RotinaSemanalDto? = null,
    @SerializedName("refeicaoDiariaDtos") var refeicaoDiaria: MutableList<RefeicaoDiariaDto>? = mutableListOf(),
    var dia: Int? = null,
    var concluido: Int? = null,
    var totalExercicios: Int? = null,
    var totalExerciciosConcluidos: Int? = null
)

data class RotinaSemanalDto(
    var id: Int? = null,
    var concluido: Int? = null
)

data class RefeicaoDiariaDto(
    var idRefeicaoDiaria: Int? = null,
    var concluido: Int? = null
)