package com.example.vitalisapp.DTO.Usuario

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.google.gson.annotations.SerializedName

data class UsuariosDto(
    var idUsuario: Int? = null,
    var nickname: String? = null,
    var cpf: String? = null,
    var nome: String? = null,
    var dtNasc: String? = null, // Retorna string da API
    var sexo: String? = null,
    var email: String? = null,
    var tipo: TipoUsuario? = null,
    @SerializedName("midiaDto") var midia: MidiaDto? = null
) {}