package com.example.vitalisapp.DTO.RefeicaoDiaria

import com.example.vitalisapp.View.Midia.Midia
import com.google.gson.annotations.SerializedName

data class RefeicaoDiariaExibitionDto (
    val idRefeicaoDiaria: Int,
    @SerializedName("refeicaoId") val refeicao: RefeicaoDto,
    @SerializedName("rotinaDiariaId") val rotinaDiaria: RotinaDiariaDto,
    val concluido: Int
)

data class RefeicaoDto (
    val idRefeicao: Int,
    val nome: String,
    val preparo: String,
    @SerializedName("midia") val midias: MutableList<Midia>
)

data class RotinaDiariaDto (
    val idRotinaDiaria: Int,
    val concluido: Int
)