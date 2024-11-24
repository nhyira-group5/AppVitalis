package com.example.vitalisapp.DTO.Perfil

import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PerfilUsuariosDto(
    val idUsuario: Int,
    val tipo: TipoUsuario,
    val nome: String,
    val nickname: String,
    val email: String,
    @SerializedName("cpf") val cpf: String,
    val dtNasc: LocalDate,
    val sexo: String,
    val telefone: String,
    val midia: MidiaDto,
    val pagamentoAtivo: Boolean,
    val fumante: Boolean,
    val alcoolatra: Boolean,
    val deficiente: Boolean,
    val problemaCardiaco: Boolean,
    val peso: String,
    val altura: String,
    val meta: String
)


data class PersonalPerfilDto(
    val idPersonal: Int,
    val nome: String,
    val nickname: String,
    val email: String,
    @SerializedName("cpf") val cpf: String,
    val dtNasc: LocalDate,
    val sexo: String,
    val telefone: String,
    val midia: MidiaDto,
    val especialidades: String,
    val lougradouro: String,
    val numero: Int,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val complemento: String?
)