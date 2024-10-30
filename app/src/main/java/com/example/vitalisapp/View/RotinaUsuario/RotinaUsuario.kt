package com.example.vitalisapp.View.RotinaUsuario

import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.View.Usuario.Usuario
import com.google.gson.annotations.SerializedName

data class RotinaUsuario (
    var idRotinaUsuario: Int,
    @SerializedName("usuarioId") var usuario: Usuario,
    @SerializedName("metaId") var meta: MetaExibitionDto,       // Não acho necesário DTO a parte
    var rotinaAlternativa: Int
)