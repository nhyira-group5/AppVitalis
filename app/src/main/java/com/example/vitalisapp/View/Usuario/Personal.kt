package com.example.vitalisapp.View.Usuario

data class Personal (
    val nome:String,
    val nickname:String,
    val cpf:String,
    val dtNasc: String,
    val senha: String,
    val sexo:String,
    val email:String,
    val tipo: TipoUsuario,
    var academiaId: Int? = null
)


