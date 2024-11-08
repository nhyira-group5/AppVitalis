package com.example.vitalisapp.DTO.Usuario

import com.example.vitalisapp.View.Usuario.TipoUsuario

data class UsuarioExibitionAltDto (
    val idUsuario: Int,
    val nickname: String,
    val cpf: String,
    val nome: String,
    val dtNasc: String,
    val sexo: String,
    val email: String,
    val tipo: TipoUsuario,
    val midiaDto: MidiaDto
)