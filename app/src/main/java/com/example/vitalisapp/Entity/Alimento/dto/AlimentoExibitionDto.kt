package com.example.vitalisapp.Entity.Alimento.dto

import com.example.vitalisapp.Entity.Midia.dto.MidiaExibitionDto
import com.google.gson.annotations.SerializedName

data class AlimentoExibitionDto (
    @SerializedName("id_alimento") var idAlimento: Int,
    var nome: String,
    var carboidrato: Double,
    var proteina: Double,
    var gordura: Double,
    var midias: List<MidiaExibitionDto>
)