package com.example.vitalisapp.View.Usuario

data class PersonalGet(
    var id: Int,
    var nome:String,
    var nickname:String,
    var cpf:String,
    var dtNasc: String,
    var senha: String,
    var sexo:String,
    var email:String,
    var tipo: TipoUsuario,
    var academiaId: Int
)