package com.example.vitalisapp.Entity.Exercicio.dto

import com.google.gson.annotations.SerializedName

data class ExercicioExibitionDto (
    val idExercicio: Int,
    val nome: String,
    val descricao: String,
    @SerializedName("idMidia") val midias: MutableList<MidiaDto>,
    @SerializedName("TagExerciciosDtos") val tags: MutableList<TagDto>
)

data class MidiaDto(
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)

data class TagDto (
    val idTag: Int,
    val nome: String
)