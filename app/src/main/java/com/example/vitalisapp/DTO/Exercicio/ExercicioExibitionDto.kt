package com.example.vitalisapp.DTO.Exercicio

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.google.gson.annotations.SerializedName

data class ExercicioExibitionDto (
    var idExercicio: Int? = null,
    var nome: String? = null,
    var descricao: String? = null,
    @SerializedName("idMidia") var midias: MutableList<MidiaDto>? = null,
    @SerializedName("TagExerciciosDtos") var tags: MutableList<TagDto>? =  null
)

data class TagDto (
    var idTag: Int? = null,
    var nome: String? = null
)