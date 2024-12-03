package com.example.vitalisapp.View.Usuario

data class UsuarioGet(
    val id: Int? = null,
    val nome: String? = null,
    val nickname: String? = null,
    val cpf: String? = null,
    val dtNasc: String? = null,
    val sexo: String? = null,
    val email: String? = null,
    val tipo: TipoUsuario? = null,
    val peso: String? = null,
    val altura: String? = null,
    val meta: Meta? = null,
    val midia: Midia? = null,
    //val personalId: Personal? = null
)



data class Meta(
    val id: Int,
    val nome: String
)

data class Midia(
    val idMidia: Int,
    val nome: String,
    val caminho: String,
    val extensao: String,
    val tipo: String
)
