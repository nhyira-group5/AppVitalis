package com.example.vitalisapp.Entity.Usuario

import com.example.vitalisapp.View.Usuario.TipoUsuario

data class loginRetornoUsuario (
    val id: Int? = null,
    val nome: String? = null,
    val email: String? = null,
    val token: String? = null,
    val tipo: TipoUsuario? =  null



)