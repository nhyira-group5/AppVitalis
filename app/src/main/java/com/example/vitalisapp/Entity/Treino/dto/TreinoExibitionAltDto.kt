package com.example.vitalisapp.Entity.Treino.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionAltDto(
    val idTreino: Int,
    val idExercicio: Int,
    val nome: String,
    val descricao: String,
    val serie: Int,
    val repeticao: Int,
    val tempo: LocalTime,
    val concluido: Integer,
    val rotinaDiaria: RotinaDiariaDto,
    @SerializedName("midia") val midias: List<MidiaDto>
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