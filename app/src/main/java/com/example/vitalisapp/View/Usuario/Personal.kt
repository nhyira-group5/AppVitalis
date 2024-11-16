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
<<<<<<< HEAD
    val academiaId: Int,
    val especialidades: String,
    val lougradouro: String,
    val numero: Int,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val complemento: String?
=======
    var academiaId: Int? = null
>>>>>>> 0670c19d396c8f259a49b45f1744d8540a135e0c
)


