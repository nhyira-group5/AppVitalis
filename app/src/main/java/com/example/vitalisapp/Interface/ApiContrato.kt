package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Contrato.ContratoExibitionDTO
import com.example.vitalisapp.DTO.Contrato.UpdateContratoRequest
import com.example.vitalisapp.View.Contrato
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiContrato {
    @GET("/contratos/por-personal/{idPersonal}")
    suspend fun showByPersonalId (@Path("idPersonal") id: Int): Response<List<Contrato>>

    @PUT("/contratos/{id}")
    suspend fun updateContratoAfiliado(
        @Path("id") id: Int,
        @Body updateRequest: UpdateContratoRequest
    ): Response<List<Contrato>>

}