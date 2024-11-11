package com.example.vitalisapp.DTO.EspecialidadePorPersonal

import com.example.vitalisapp.DTO.Especialidade.EspecialidadeExibitionDto
import com.example.vitalisapp.DTO.Usuario.UsuariosDto
import com.google.gson.annotations.SerializedName

data class EspecialidadePersonalExibitionDto(
    var idEspecialidadePersonal: Int? = null,
    @SerializedName("personalId") var personal: UsuariosDto? = null,
    @SerializedName("especialidadeId") var especialidade: EspecialidadeExibitionDto? = null
) {  }