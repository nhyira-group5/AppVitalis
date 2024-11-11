package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Refeicao.RefeicaoExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRefeicao {
    @GET("/refeicoes/{id}") // idRefeicao
    suspend fun showById (@Path("id") id: Int): Response<RefeicaoExibitionDto>

    @GET("/refeicoes")
    suspend fun showAll (): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-meta/{idMeta}")    // idMeta
    suspend fun showByMeta (@Path("idMeta") idMeta: Int): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-dieta/{idDieta}")  // idDieta
    suspend fun showByDieta (@Path("idDieta") idDieta: Int): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-refeicao-diaria/{idRefeicaoDiaria}")  // idRefeicaoDiaria
    suspend fun showByRefeicaoDiaria (@Path("idRefeicaoDiaria") idRefeicaoDiaria: Int): Response<RefeicaoExibitionDto>

    @GET("/refeicoes/filtro/nome")
    suspend fun showByNome (@Query("nome") nome: String): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-meta/{idMeta}/filtro/nome")
    suspend fun showByMetaAndName (@Path("idMeta") idMeta: Int, @Query("nome") nome: String): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-semana/{idRotinaSemanal}") // idRotinaSemanal
    suspend fun showByRotinaSemanal (@Path("idRotinaSemanal") idRotinaSemanal: Int): Response<MutableList<RefeicaoExibitionDto>>

    @GET("/refeicoes/por-dia/{idRotinaDiaria}") // idRotinaDiaria
    suspend fun showByidRotinaDiaria (@Path("idRotinaDiaria") idRotinaDiaria: Int): Response<MutableList<RefeicaoExibitionDto>>
}