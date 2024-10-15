package com.example.vitalisapp.DTO.RotinaUsuario


import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.DTO.Usuario.UsuarioExibitionAltDto
import com.google.gson.annotations.SerializedName

data class RotinaUsuarioExibitionDto (
    val idRotinaUsuario: Int,
    @SerializedName("usuarioId") val usuario: UsuarioExibitionAltDto,
    @SerializedName("metaId") val meta: MetaExibitionDto,               // Não acho necesário DTO a parte
    val rotinaAlternativa: Boolean
)