package com.example.vitalisapp.Interface

import com.example.vitalisapp.Entity.Treino.dto.TreinoExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTreino {
    @GET("/treinos/{id}")
    suspend fun showById(@Path("id") id: Int): Response<TreinoExibitionDto>

    @PATCH("/treinos/concluir/{id}")
    suspend fun concluir(@Path("id") id: Int, @Query("concluido") concluido: Int): Response<TreinoExibitionDto>
}