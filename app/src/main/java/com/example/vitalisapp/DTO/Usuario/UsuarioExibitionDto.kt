package com.example.vitalisapp.DTO.Usuario

import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.View.RotinaUsuario.RotinaUsuario
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UsuarioExibitionDto (
    var idUsuario: RotinaUsuario? = null,
    @SerializedName("personalId") var personal: UsuarioExibitionAltDto? = null,
    @SerializedName("academiaId") var academia: EnderecoAcademiaDto? = null,
    var meta: MetaExibitionDto? = null,     // Não acho necesário DTO a parte
    var nickname: String? = null,
    var cpf: String? = null,
    var nome: String? = null,
    var dtNasc: String? = null, // Retorno em String da API
    var sexo: String? = null,
    var email: String? = null,
    var tipo: TipoUsuario? = null,
    var midia: MidiaDto? = null,
    var pontos: Int? = null,
    var pagamentoAtivo: Boolean? = null
)

data class EnderecoAcademiaDto (
    @SerializedName("id") var idEndereco: Int? = null,
    var lougradouro: String? = null,
    var numero: Int? = null,
    var bairro: String? = null,
    var cidade: String? = null,
    var estado: String? = null,
    var cep: String? = null,
    var complemento: String? = null
)