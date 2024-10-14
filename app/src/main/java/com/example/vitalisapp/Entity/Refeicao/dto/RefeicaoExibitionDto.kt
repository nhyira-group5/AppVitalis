package com.example.vitalisapp.Entity.Refeicao.dto

import com.example.vitalisapp.Entity.Meta.dto.MetaExibitionDto
import com.google.gson.annotations.SerializedName

data class RefeicaoExibitionDto (
    val idRefeicao: Int,
    val nome: String,
    val preparo: String,
    @SerializedName("midia") val midias: List<MidiaDto>,
    @SerializedName("alimentoPorRefeicao") val alimentos: List<AlimentoPorRefeicaoDto>
)

data class AlimentoPorRefeicaoDto (
    val idAlimentoRefeicao: Int,
    val qtdAlimento: Int,
    val alimentoDto: AlimentoDto,
    val metrica: MetaExibitionDto       // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
)

data class MidiaDto (
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String,
)

data class AlimentoDto (
    @SerializedName("id") val idAlimento: Int,
    val nome: String,
    val carboidrato: Double,
    val proteina: Double,
    val gordura: Double,
    val midia: List<MidiaDto>,
)