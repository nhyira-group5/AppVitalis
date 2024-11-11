package com.example.vitalisapp.DTO.Treino

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionAltDto(
    val idTreino: Int? = null,
    val idExercicio: Int? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val serie: Int? = null,
    val repeticao: Int? = null,
    val tempo: String? = null,
    val concluido: Int? = null,
    val rotinaDiaria: RotinaDiariaDto? = null,
    @SerializedName("midia") val midias: MutableList<MidiaDto>? = null
)

data class RotinaDiariaDto(
    @SerializedName("id") val idRotinaDiaria: Int? = null,
    val dia: Int? = null
)