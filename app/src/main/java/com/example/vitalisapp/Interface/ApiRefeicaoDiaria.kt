package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RefeicaoDiaria.RefeicaoDiariaExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRefeicaoDiaria {
    @PATCH("/refeicaoDiarias/concluir/{id}")
    suspend fun setComplete (@Path("id") id: Int, @Query("concluido") concluido: Int): Response<RefeicaoDiariaExibitionDto>

    @GET("/refeicaoDiarias/{id}")
    suspend fun showById (@Path("id") id: Int): Response<RefeicaoDiariaExibitionDto>
}