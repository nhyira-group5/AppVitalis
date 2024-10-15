package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.RotinaMensal.RotinaMensalExibitionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRotinaMensal {
    @GET("/rotinaMensais/{id}")
    suspend fun showById(@Path("id") id: Int): Response<RotinaMensalExibitionDto>

    @GET("/rotinaMensais/{id}/mes")
    suspend fun showByUserRoutineIdAndMonth(@Path("id") idRotinaUsuario: Int, @Query("mes") mes: Int): Response<RotinaMensalExibitionDto>

    @PATCH("/rotinaMensais/concluir/{id}")
    suspend fun setComplete(@Path("id") id: Int, @Query("concluido") mes: Int): Response<RotinaMensalExibitionDto>

    @GET("/rotinaMensais/buscarIdUsuario/{id}/mes")
    suspend fun showByUserIdAndMonth(@Path("id") idUsuario: Int, @Query("mes") mes: Int): Response<RotinaMensalExibitionDto>
}