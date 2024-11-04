package com.example.vitalisapp.DTO.RotinaUsuario

import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.DTO.Usuario.UsuarioExibitionAltDto
import com.google.gson.annotations.SerializedName

data class RotinaUsuarioExibitionDto (
    var idRotinaUsuario: Int ? = null,
    @SerializedName("usuarioId") var usuario: UsuarioExibitionAltDto ? = null,
    @SerializedName("metaId") var meta: MetaExibitionDto ? = null,               // Não acho necesário DTO a parte
    var rotinaAlternativa: Boolean ? = null
)