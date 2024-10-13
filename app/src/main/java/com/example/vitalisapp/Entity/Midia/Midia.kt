package com.example.vitalisapp.Entity.Midia

import com.google.gson.annotations.SerializedName

data class Midia (
    @SerializedName("id_midia") var idMidia: Int,
    var nome: String,
    var caminho: String,
    var extensao: String,
    var tipo: String
)