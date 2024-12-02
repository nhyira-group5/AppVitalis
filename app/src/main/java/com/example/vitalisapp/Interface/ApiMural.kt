package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Mural.MuralExibitionDto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMural {
    @GET("/murais/{id}") // idMural
    suspend fun showById (@Path("id") id: Int): Response<List<MuralExibitionDto>>

    @GET("/murais/por-usuario/{id}") // idUsuario
    suspend fun showByUsuarioId (@Path("id") id: Int): Response<List<MuralExibitionDto>>

}