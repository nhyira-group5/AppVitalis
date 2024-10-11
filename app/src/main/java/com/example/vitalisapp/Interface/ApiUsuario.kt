package com.example.vitalisapp.Interface

import com.example.vitalisapp.Entity.Usuario.Usuario
import com.example.vitalisapp.Entity.Usuario.UsuarioGet
import com.example.vitalisapp.Entity.Usuario.loginUsuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUsuario {

    @POST("/usuarios")
    suspend fun postUsuario(@Body usuario: Usuario): Response<UsuarioGet>

    @POST("/login/usuario")
    suspend fun loginUsuario(@Body loginUsuario: loginUsuario): Response<loginUsuario>



}