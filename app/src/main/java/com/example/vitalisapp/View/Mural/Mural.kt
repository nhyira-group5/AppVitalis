package com.example.vitalisapp.View.Mural

import java.time.LocalDate

data class Mural(
    var idMural: Int,
    var usuarioId: Usuario,
    var midia: Midia,
    var dtPostagem: LocalDate
)

data class Usuario(
    var idUsuario: Int,
    var nickname: String,
    var nome: String,
    var tipo: String
)

data class Midia(
    var id: Int,
    var nome: String,
    var caminho: String,
    var extensao: String,
    var tipo: String
)
