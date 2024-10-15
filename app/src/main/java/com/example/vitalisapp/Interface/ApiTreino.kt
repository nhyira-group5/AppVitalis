package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Treino.TreinoExibitionAltDto
import com.example.vitalisapp.DTO.Treino.TreinoExibitionDto
import com.example.vitalisapp.DTO.Treino.TreinoRelatorioDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTreino {
    @GET("/treinos/{idRotinaDiaria}")   // idRotinaDiaria
    suspend fun showByRotinaDiariaId( @Path("idRotinaDiaria") idRotinaDiaria: Int): Response<MutableList<TreinoExibitionDto>>

    @PATCH("/treinos/concluir/{id}")    // idTreino
    suspend fun setComplete (@Path("id") id: Int, @Query("concluido") concluido: Int): Response<TreinoExibitionDto>

    @GET("/treinos/relatorio/{id}") // idRotinaMensal
    suspend fun showByRotinaMensalIdForReport (@Path("id") id: Int): Response<MutableList<TreinoRelatorioDto>>

    @GET("/treinos/buscarIdTreinos/{id}")   // idTreino
    suspend fun showById (@Path("id") id: Int): Response<TreinoExibitionDto>

    @GET("/treinos/por-semana/{idRotinaSemanal}") // idRotinaSemanal
    suspend fun showByRotinaSemanalId (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<MutableList<TreinoExibitionAltDto>>             // Exibition ALTERNATIVO DTO

    @GET("/treinos/por-dia/{idRotinaDiaria}")   // idRotinaDiaria
    suspend fun showByRotinaDiariaIdExibitionSemanal (@Path("idRotinaDiaria") idRotinaDiaria: Int): Response<MutableList<TreinoExibitionAltDto>>      // Exibition ALTERNATIVO DTO
}