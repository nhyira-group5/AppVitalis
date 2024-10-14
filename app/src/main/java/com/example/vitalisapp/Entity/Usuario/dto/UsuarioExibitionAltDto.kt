package com.example.vitalisapp.Entity.Usuario.dto

import com.example.vitalisapp.Entity.RotinaUsuario.RotinaUsuario
import com.example.vitalisapp.Entity.Usuario.TipoUsuario
import java.time.LocalDate

data class UsuarioExibitionAltDto (
    val idUsuario: RotinaUsuario,
    val nickname: String,
    val cpf: String,
    val nome: String,
    val dtNasc: LocalDate,
    val sexo: String,
    val email: String,
    val tipo: TipoUsuario,
    val midiaDto: MidiaDto
)