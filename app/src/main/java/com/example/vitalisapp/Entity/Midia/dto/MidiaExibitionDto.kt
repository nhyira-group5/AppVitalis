package com.example.vitalisapp.Entity.Midia.dto

import com.google.gson.annotations.SerializedName

data class MidiaExibitionDto (
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)