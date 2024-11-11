package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Usuario.PersonalExibitionDto
import com.example.vitalisapp.DTO.Usuario.UsuarioExibitionDto
import com.example.vitalisapp.Entity.Usuario.loginRetornoUsuario
import com.example.vitalisapp.View.Usuario.Usuario
import com.example.vitalisapp.View.Usuario.UsuarioGet
import com.example.vitalisapp.View.Usuario.loginUsuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUsuario {

    @POST("/usuarios")
    suspend fun postUsuario(@Body usuario: Usuario): Response<UsuarioGet>

    @POST("/login/usuario")
    suspend fun loginUsuario(@Body loginUsuario: loginUsuario): Response<loginRetornoUsuario>

    @GET("/personal/{idUser}")
    suspend fun showTrainersByUserId(@Path("idUser") idUser: Int): Response<UsuarioExibitionDto>

    @GET("/usuarios/personais")
    suspend fun showTrainers(): Response<MutableList<PersonalExibitionDto>>

    @GET("/usuarios/personais-por-meta/{idMeta}")
    suspend fun showTrainersByMetaId(@Path("idMeta") idMeta: Int): Response<MutableList<PersonalExibitionDto>>
}