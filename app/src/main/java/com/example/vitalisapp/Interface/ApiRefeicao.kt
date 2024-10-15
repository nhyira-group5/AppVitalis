package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Refeicao.RefeicaoExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRefeicao {
    @GET("/refeicoes/{id}")
    suspend fun showById (@Path("id") id: Int): Response<RefeicaoExibitionDto>

    @GET("/refeicoes")
    suspend fun showAll (): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-meta/{idMeta}")
    suspend fun showByMetaId (@Path("idMeta") idMeta: Int): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-dieta/{idDieta}")
    suspend fun showByDietaId (@Path("idDieta") idDieta: Int): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/filtro/nome")
    suspend fun showByNome (@Query("nome") nome: String): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-semana/{idRotinaSemanal}")
    suspend fun showByRotinaSemanalId (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<MutableList<RefeicaoExibitionDto>>
}