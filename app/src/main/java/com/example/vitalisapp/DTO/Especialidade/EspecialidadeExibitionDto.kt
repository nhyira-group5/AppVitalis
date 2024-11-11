package com.example.vitalisapp.DTO.Especialidade

import com.google.gson.annotations.SerializedName

data class EspecialidadeExibitionDto(
    @SerializedName("id") var idEspecialidade: Int? = null,
    var nome: String? = null
) {  }