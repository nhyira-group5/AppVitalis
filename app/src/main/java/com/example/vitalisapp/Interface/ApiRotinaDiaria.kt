package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRotinaDiaria {
    @GET("/rotinaDiarias/por-semana/{id}")
    suspend fun showByRotinaSemanalId (@Path("id") idRotinaSemanal: Int): Response<MutableList<RotinaDiariaExibitionDto>>

    @GET("/rotinaDiarias/{id}")
    suspend fun showById (@Path("id") id: Int): Response<RotinaDiariaExibitionDto>

    @GET("/rotinaDiarias/por-usuario/{id}")
    suspend fun showByUsuarioId (@Path("id") idUsuario: Int): Response<MutableList<RotinaDiariaExibitionDto>>

    @PATCH("/rotinaDiarias/concluir/{id}")
    suspend fun setComplete (@Path("id") id: Int, @Query("concluido") concluido: Int): Response<RotinaDiariaExibitionDto>
}