package com.example.vitalisapp.Entity.RefeicaoDiaria.dto

import com.example.vitalisapp.Entity.Midia.Midia
import com.google.gson.annotations.SerializedName

data class RefeicaoDiariaExibitionDto (
    val idRefeicaoDiaria: Int,
    @SerializedName("refeicaoId") val refeicao: RefeicaoDto,
    @SerializedName("rotinaDiariaId") val rotinaDiaria: RefeicaoDiariaDto,
    val concluido: Int
)

data class RefeicaoDto (
    val idRefeicao: Int,
    val nome: String,
    val preparo: String,
    @SerializedName("midia") val midias: MutableList<Midia>
)

data class RefeicaoDiariaDto (
    val idRotinaDiaria: Int,
    val concluido: Int
)