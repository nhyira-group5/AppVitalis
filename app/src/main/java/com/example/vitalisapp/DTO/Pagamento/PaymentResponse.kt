package com.example.vitalisapp.DTO.Pagamento

data class PaymentResponse(
    val id: String,
    val status: String,
    val details: Map<String, Any>
)