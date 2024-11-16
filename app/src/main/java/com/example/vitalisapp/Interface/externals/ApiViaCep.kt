package com.example.vitalisapp.Interface.externals

import com.example.vitalisapp.DTO.Endereco.ViaCepDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiViaCep {
    @POST("/ws/{cep}/json/")
    suspend fun getLocationByViaCepApi(@Path("cep") cep: Int): Response<ViaCepDto>
}