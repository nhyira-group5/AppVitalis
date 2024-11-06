package com.example.vitalisapp.Interface

import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import com.example.vitalisapp.DTO.Pagamento.PaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiPagamento {
    @POST("/pagamentos/criar")
    suspend fun createPayment(@Body pagamentoDto: PagamentoCreateEditDto): Response<PaymentResponse>

    @GET("/pagamentos/{idPagamentoMercPag}")
    suspend fun showById(@Path("idPagamentoMercPag") idPagamentoMercPag: Int): Response<PaymentResponse>
}