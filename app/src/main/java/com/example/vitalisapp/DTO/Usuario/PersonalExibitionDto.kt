package com.example.vitalisapp.DTO.Usuario

import com.example.vitalisapp.DTO.Especialidade.EspecialidadeExibitionDto
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.google.gson.annotations.SerializedName

data class PersonalExibitionDto(
    var idPersonal: Int? = null,
    var nickname: String? = null,
    var cpf: String? = null,
    var nome: String? = null,
    var dtNasc: String? = null,
    var sexo: String? = null,
    var email: String? = null,
    var tipo: TipoUsuario? = null,
    @SerializedName("exibitonDto") var especialidades: MutableList<EspecialidadePersonalDto>? = mutableListOf(),
    @SerializedName("midiaDto") var midia: MidiaDto? = null
) {  }

data class EspecialidadePersonalDto(
    @SerializedName("id") var idEspecialidadePersonal: Int? = null,
    @SerializedName("especialidadeId") var especialidade: EspecialidadeExibitionDto? = null
) {  }