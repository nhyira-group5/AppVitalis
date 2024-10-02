package com.example.vitalisapp.Entity.Usuario

data class Usuario(
    val nome:String,
    val nickname:String,
    val cpf:String,
    val dtNasc: String,
    val senha: String,
    val sexo:String,
    val email:String,
    val tipo: TipoUsuario,
)