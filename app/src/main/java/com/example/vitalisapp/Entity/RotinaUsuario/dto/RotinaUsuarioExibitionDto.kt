package com.example.vitalisapp.Entity.RotinaUsuario.dto


import com.example.vitalisapp.Entity.Meta.dto.MetaExibitionDto
import com.example.vitalisapp.Entity.Usuario.dto.UsuarioExibitionAltDto
import com.google.gson.annotations.SerializedName

data class RotinaUsuarioExibitionDto (
    val idRotinaUsuario: Int,
    @SerializedName("usuarioId") val usuario: UsuarioExibitionAltDto,
    @SerializedName("metaId") val meta: MetaExibitionDto,       // Não acho necesário DTO a parte
    val rotinaAlternativa: Boolean
)