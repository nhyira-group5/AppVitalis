package com.example.vitalisapp.DTO.Usuario

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.View.Usuario.TipoUsuario

data class UsuarioExibitionAltDto (
    var idUsuario: Int? = null,
    var nickname: String? = null,
    var cpf: String? = null,
    var nome: String? = null,
    var dtNasc: String? = null,
    var sexo: String? = null,
    var email: String? = null,
    var tipo: TipoUsuario? = null,
    var midiaDto: MidiaDto? = null
)