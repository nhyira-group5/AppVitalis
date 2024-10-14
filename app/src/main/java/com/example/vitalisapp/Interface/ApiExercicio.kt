package com.example.vitalisapp.Interface

import com.example.vitalisapp.Entity.Exercicio.dto.ExercicioExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiExercicio {
    @GET("/exercicios")
    suspend fun showAll (): Response<List<ExercicioExibitionDto>>

    @GET("/exercicios/{id}")
    suspend fun showById (@Path("id") id: Int): Response<ExercicioExibitionDto>

    @GET("/exercicios/buscarPorTreino/{id}")
    suspend fun showByTreinoId (@Path("id") idTreino: Int): Response<ExercicioExibitionDto>
}