package com.example.vitalisapp.Entity.Refeicao

import com.google.gson.annotations.SerializedName

data class Refeicao (
    @SerializedName("id_refeicao") var idRefeicao: Int,
    var nome: String,
    var preparo: String
)