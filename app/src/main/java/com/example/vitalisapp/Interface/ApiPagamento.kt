package com.example.vitalisapp.Interface

import PaymentResponse
import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiPagamento {
    @POST("/pagamentos/criar")
    suspend fun createPayment(@Body pagamentoDto: PagamentoCreateEditDto): Response<PaymentResponse>

    @GET("/pagamentos/{idPagamentoMercPag}")
    suspend fun showById(@Path("idPagamentoMercPag") idPagamentoMercPag: String): Response<PaymentResponse>
}