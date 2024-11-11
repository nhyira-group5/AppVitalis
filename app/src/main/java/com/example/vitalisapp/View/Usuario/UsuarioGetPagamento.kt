package com.example.vitalisapp.View.Usuario

import com.example.vitalisapp.DTO.Meta.MetaExibitionDto

data class UsuarioGetPagamento(
    val id: Int? = null,
    val nome:String ? = null,
    val nickname:String ? = null,
    val cpf:String ? = null,
    val dtNasc: String ? = null,
    val sexo:String ? = null,
    val email:String ? = null,
    val tipo: TipoUsuario ? = null,
    val meta: MetaExibitionDto? = null,
    val pagamentoAtivo: Boolean? = null
) {
}