package com.example.vitalisapp.Entity.Exercicio

import com.google.gson.annotations.SerializedName

data class Exercicio (
    var idExercicio: Int,
    var nome: String,
    var descricao: String
)