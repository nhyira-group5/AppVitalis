package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRotinaDiaria {
    @GET("/rotinaDiarias/por-semana/{id}")  // idRotinaSemanal
    suspend fun showByRotinaSemanalId (@Path("id") id: Int): Response<MutableList<RotinaDiariaExibitionDto>>

    @GET("/rotinaDiarias/diaria-atual/{idRotinaSemanal}") // idRotinaSemanal
    suspend fun showCurrentDailyRoutine (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<RotinaDiariaExibitionDto>

    @GET("/rotinaDiarias/{id}") // idRotinaDiaria
    suspend fun showById (@Path("id") id: Int): Response<RotinaDiariaExibitionDto>

    @GET("/rotinaDiarias/por-usuario/{id}") // idUsuario
    suspend fun showByUsuarioId (@Path("id") id: Int): Response<MutableList<RotinaDiariaExibitionDto>>

    @PATCH("/rotinaDiarias/concluir/{id}")  // idRotinaDiaria
    suspend fun setComplete (@Path("id") id: Int, @Query("concluido") concluido: Int): Response<RotinaDiariaExibitionDto>
}