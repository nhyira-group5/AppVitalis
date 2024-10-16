package com.example.vitalisapp.DTO.Treino

import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionAltDto(
    val idTreino: Int,
    val idExercicio: Int,
    val nome: String,
    val descricao: String,
    val serie: Int,
    val repeticao: Int,
    val tempo: String,
    val concluido: Int,
    val rotinaDiaria: RotinaDiariaDto,
    @SerializedName("midia") val midias: MutableList<MidiaDto>
)

data class MidiaDto(
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)

data class RotinaDiariaDto(
    @SerializedName("id") val idRotinaDiaria: Int,
    val dia: Int
)