package com.example.vitalisapp.DTO.RotinaSemanal

import com.google.gson.annotations.SerializedName

data class RotinaSemanalExibitionDto (
    @SerializedName("id") var idRotinaSemanal: Int? = null,
    @SerializedName("rotinaMensalId") var rotinaMensal: RotinaMensalDto? = null,
    @SerializedName("rotinaDiariaDtos") var rotinasDiarias: MutableList<RotinaDiariaDto>? = mutableListOf(),
    var numSemana: Int? = null,
    var concluido: Int? = null
)

data class RotinaMensalDto(
    var id: Int? = null,
    var mes: Int? = null,
    var ano: Int? = null
)

data class RotinaDiariaDto(
    var idRotinaDiaria: Int? = null,
    var concluido: Int? = null
)