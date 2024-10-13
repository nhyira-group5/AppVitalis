package com.example.vitalisapp.Entity.Refeicao.dto

import com.example.vitalisapp.Entity.Alimento.dto.AlimentoExibitionDto
import com.example.vitalisapp.Entity.Midia.dto.MidiaExibitionDto
import com.google.gson.annotations.SerializedName

data class RefeicaoExibitionDto (
    @SerializedName("id_refeicao") val idRefeicao: Int,
    val nome: String,
    val preparo: String,
    val midias: List<MidiaExibitionDto>,
    val ingredientes: List<AlimentoExibitionDto>
)