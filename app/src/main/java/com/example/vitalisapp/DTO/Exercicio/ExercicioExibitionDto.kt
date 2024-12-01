package com.example.vitalisapp.DTO.Exercicio

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.google.gson.annotations.SerializedName

data class ExercicioExibitionDto(
    var idExercicio: Int? = null,
    var nome: String? = null,
    var descricao: String? = null,
    @SerializedName("idMidia") var midias: MutableList<MidiaDto>? = null,
    @SerializedName("tagExercicioDtos") var tagExercicioDtos: MutableList<TagExercicioDto>? = null // Novo campo
)

data class TagExercicioDto(
    var idTagExercicio: Int? = null,
    @SerializedName("tagId") var tag: TagDto? = null
)

data class TagDto(
    var idTag: Int? = null,
    var nome: String? = null
)