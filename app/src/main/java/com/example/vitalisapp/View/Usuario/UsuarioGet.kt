package com.example.vitalisapp.View.Usuario

data class UsuarioGet(
    val id: Int,
    val nome:String,
    val nickname:String,
    val cpf:String,
    val dtNasc: String,
    val sexo:String,
    val email:String,
    val tipo: TipoUsuario,
)