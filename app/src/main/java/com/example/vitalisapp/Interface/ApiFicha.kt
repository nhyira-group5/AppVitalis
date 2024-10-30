package com.example.vitalisapp.Interface


import com.example.vitalisapp.Entity.Ficha.ExibitionFicha
import com.example.vitalisapp.Entity.Ficha.FichaCriacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiFicha {

    @POST("/fichas")
    suspend fun create(@Body fichaCriacao: FichaCriacao): Response<ExibitionFicha>

    @GET("/fichas/{id}")
    suspend fun getFicha(@Path("id") id: Int?):Response<ExibitionFicha>
}