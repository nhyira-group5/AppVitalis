package com.example.vitalisapp.DTO.Refeicao

import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.google.gson.annotations.SerializedName

data class RefeicaoExibitionDto (
    var idRefeicao: Int? = null,
    var nome: String? = null,
    var preparo: String? = null,
    @SerializedName("midia") var midias: MutableList<MidiaDto>? = mutableListOf(),
    @SerializedName("alimentoPorRefeicao") var alimentos: MutableList<AlimentoPorRefeicaoDto>? = mutableListOf()
)

data class AlimentoPorRefeicaoDto (
    var idAlimentoRefeicao: Int? = null,
    var qtdAlimento: Int? = null,
    var alimento: AlimentoDto? = null,
    var metrica: MetaExibitionDto? = null       // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
)

data class AlimentoDto (
    @SerializedName("id") var idAlimento: Int? = null,
    var nome: String? = null,
    var carboidrato: Double? = null,
    var proteina: Double? = null,
    var gordura: Double? = null,
    var midia: MutableList<MidiaDto>? = mutableListOf(),
)