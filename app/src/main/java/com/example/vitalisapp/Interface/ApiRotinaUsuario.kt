package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioCreateEditDto
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioExibitionDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRotinaUsuario {
    @POST("/rotinaUsuarios")
    suspend fun create(@Body rotinaUsuarioDto: RotinaUsuarioCreateEditDto): Response<RotinaUsuarioExibitionDto>

    @GET("/rotinaUsuarios/{id}")    // idUsuario
    suspend fun showByUserId(@Path("id") id: Int): Response<RotinaUsuarioExibitionDto>
}