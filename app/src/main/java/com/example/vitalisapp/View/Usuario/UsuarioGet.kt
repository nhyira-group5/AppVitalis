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
    val fumante: Boolean,
    val alcoolatra: Boolean,
    val deficiente: Boolean,
    val problemaCardiaco: Boolean,
    val peso: String,
    val altura: String,
    val meta: String,
)