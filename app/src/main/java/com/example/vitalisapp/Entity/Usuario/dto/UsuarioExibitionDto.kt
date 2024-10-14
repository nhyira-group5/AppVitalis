package com.example.vitalisapp.Entity.Usuario.dto

import com.example.vitalisapp.Entity.Meta.dto.MetaExibitionDto
import com.example.vitalisapp.Entity.RotinaUsuario.RotinaUsuario
import com.example.vitalisapp.Entity.Usuario.TipoUsuario
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UsuarioExibitionDto (
    val idUsuario: RotinaUsuario,
    @SerializedName("personalId") val personal: UsuarioExibitionAltDto,
    @SerializedName("academiaId") val academia: EnderecoAcademiaDto,
    val meta: MetaExibitionDto,     // Não acho necesário DTO a parte
    val nickname: String,
    val cpf: String,
    val nome: String,
    val dtNasc: LocalDate,
    val sexo: String,
    val email: String,
    val tipo: TipoUsuario,
    val midia: MidiaDto,
    val pontos: Int,
    val pagamentoAtivo: Boolean
)

data class MidiaDto (
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)

data class EnderecoAcademiaDto (
    @SerializedName("id") val idEndereco: Int,
    val lougradouro: String,
    val numero: Int,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val complemento: String
)