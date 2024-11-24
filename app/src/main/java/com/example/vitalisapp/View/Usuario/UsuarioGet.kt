package com.example.vitalisapp.View.Usuario

data class UsuarioGet(
    val id: Int? = null,
    val nome:String ? = null,
    val nickname:String ? = null,
    val cpf:String ? = null,
    val dtNasc: String ? = null,
    val sexo:String ? = null,
    val email:String ? = null,
    val tipo: TipoUsuario ? = null,
    val peso: String? = null,
    val altura: String? = null,
    val meta: String? = null
)