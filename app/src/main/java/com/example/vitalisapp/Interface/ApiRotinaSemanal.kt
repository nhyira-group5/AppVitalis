package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaSemanal.RotinaSemanalExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRotinaSemanal {

    @GET("/rotinaSemanais/{id}")
    suspend fun showById (@Path("id") id: Int): Response<RotinaSemanalExibitionDto>

    @GET("/rotinaSemanais/buscarUsuario/{id}")
    suspend fun showByUserId (@Path("id") id: Int): Response<MutableList<RotinaSemanalExibitionDto>>

    @GET("/rotinaSemanais/dias-treinados/{idRotinaSemanal}")
    suspend fun showCompletedDailyRoutinesByRotinaSemanalId (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<Int>

    @PATCH("/rotinaSemanais/concluir/{id}")
    suspend fun setComplete (@Path("id") id: Int, @Query("concluir") concluido: Int): Response<RotinaSemanalExibitionDto>
}