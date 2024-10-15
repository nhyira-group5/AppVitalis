package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaSemanal.RotinaSemanalExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRotinaSemanal {

    @GET("/rotinaSemanais/{id}")    // rotinaSemanais
    suspend fun showById (@Path("id") id: Int): Response<RotinaSemanalExibitionDto>

    @GET("/rotinaSemanais/por-usuario/{idUsuario}")  // idUsuario
    suspend fun showByUserId (@Path("idUsuario") idUsuario: Int): Response<MutableList<RotinaSemanalExibitionDto>>

    @GET("/rotinaSemanais/semana-atual/{idUsuario}")  // idUsuario
    suspend fun showCurrentWeekRoutineByUserId (@Path("idUsuario") idUsuario: Int): Response<RotinaSemanalExibitionDto>

    @GET("/rotinaSemanais/dias-treinados/{idRotinaSemanal}")    // idRotinaSemanal
    suspend fun showCompletedDailyRoutinesByRotinaSemanalId (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<Int>

    @PATCH("/rotinaSemanais/concluir/{id}") // idRotinaSemanal
    suspend fun setComplete (@Path("id") id: Int, @Query("concluir") concluido: Int): Response<RotinaSemanalExibitionDto>
}