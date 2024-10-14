package com.example.vitalisapp.Entity.RotinaUsuario

import com.example.vitalisapp.Entity.Meta.dto.MetaExibitionDto
import com.example.vitalisapp.Entity.Usuario.Usuario
import com.google.gson.annotations.SerializedName

data class RotinaUsuario (
    var idRotinaUsuario: Int,
    @SerializedName("usuarioId") var usuario: Usuario,
    @SerializedName("metaId") var meta: MetaExibitionDto,       // Não acho necesário DTO a parte
    var rotinaAlternativa: Int
)