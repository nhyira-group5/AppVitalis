package com.example.vitalisapp.DTO.Mural

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class MuralExibitionDto(
    val idMural: Int,
    val usuarioId: UsuarioDto,
    val midia: MidiaDto,
    val dtPostagem: String
)


data class UsuarioDto(
    @SerializedName("idUsuario") val idUsuario: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("tipo") val tipo: String
)

data class MidiaDto(
    @SerializedName("id") val id: Int,
    @SerializedName("nome") val nome: String,
    @SerializedName("caminho") val caminho: String,
    @SerializedName("extensao") val extensao: String,
    @SerializedName("tipo") val tipo: String
)
