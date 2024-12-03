package com.example.vitalisapp.View

import com.example.vitalisapp.View.Usuario.Midia

data class Contrato(
    val idContrato: Int,
    val usuarioId: UsuarioDetalhe,
    val personalId: UsuarioDetalhe,
    val inicioContrato: String,
    val fimContrato: String,
    val afiliacao: Int
)

data class UsuarioDetalhe(
    val idUsuario: Int,
    val nickname: String,
    val cpf: String,
    val nome: String,
    val dtNasc: String,
    val sexo: String,
    val email: String,
    val tipo: String,
    val midiaDto: Midia
)

