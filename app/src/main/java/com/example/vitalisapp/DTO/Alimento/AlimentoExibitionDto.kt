package com.example.vitalisapp.DTO.Alimento

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.google.gson.annotations.SerializedName

data class AlimentoExibitionDto (
    var idAlimento: Int? = null,
    var nome: String? = null,
    var carboidrato: Double? = null,
    var proteina: Double? = null,
    var gordura: Double? = null,
    @SerializedName("midia") var midias: MutableList<MidiaDto>? = null
)