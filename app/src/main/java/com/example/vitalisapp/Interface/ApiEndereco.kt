package com.example.vitalisapp.Interface

import com.example.vitalisapp.View.Endereco.CreateEndereco
import com.example.vitalisapp.View.Endereco.ExibitionEndereco
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEndereco {

    @POST("/enderecos")
    suspend fun postEndereco(@Body endereco: CreateEndereco): Response<ExibitionEndereco>
//
//    @GET("{cep}/json/")
//    fun getEndereco(@Path("cep") cep: String): Response<EnderecoResponse>
//
//    data class EnderecoResponse(
//        val cep: String? = null,
//        val logradouro: String? = null,
//        val complemento: String? = null,
//        val bairro: String? = null,
//        val localidade: String? = null,
//        val uf: String ? = null
//    )
}