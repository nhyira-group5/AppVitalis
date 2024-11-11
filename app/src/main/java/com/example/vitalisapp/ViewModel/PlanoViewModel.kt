package com.example.vitalisapp.ViewModel

import PaymentResponse
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class PlanoUiState(
    var paymentCreationObject: PaymentResponse? = null,
    var paymentObject: PaymentResponse? = null,

    // Colocando aqui para facilitar a vida algumas info do pagamento, já que retorna um Object da API
    var paymentComplete: Boolean? = null,
    var dateExpirePayment: String? = null,
    var paymentId: Long? = null,
    var qrCodeBitmap: Bitmap? = null
) {}

class PlanoViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _planoUiState = MutableStateFlow(PlanoUiState())
    val planoUiState = _planoUiState.asStateFlow()

    // Verifica o status do pagamento
    init {
        if (planoUiState.value.paymentId != null) getPayment(planoUiState.value.paymentId!!)
    }

    fun createPayment(paymentDto: PagamentoCreateEditDto) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiPagamento.createPayment(paymentDto)
                if (res.isSuccessful) {
                    _planoUiState.update { cs ->
                        cs.copy(
                            paymentCreationObject = res.body(),
                            paymentId = res.body()?.id,
                            qrCodeBitmap = convertBase64ToBitmap(res.body()?.pointOfInteraction?.transactionData?.qrCodeBase64),
                            paymentComplete = false
                        )
                    }
                    Log.i(
                        "PlanoViewModel",
                        "Sucesso ao criar o pagamento: ${planoUiState.value.paymentCreationObject}"
                    )
                    Log.i(
                        "PlanoViewModel",
                        "Sucesso ao gerar o QRCODE: ${planoUiState.value.qrCodeBitmap}"
                    )
                } else {
                    Log.e("PlanoViewModel", "Erro ao criar o pagamento: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                //Log.e("PlanoViewModel", "Erro na API para criar o pagamento: ${e.message}")
                throw ApiException("Criar pagamento", e.message)
            }
        }
    }

    private fun getPayment(paymentId: Long) {
        viewModelScope.launch {
            try {
                val convertPaymentId = paymentId.toString()
                val res = globalUiState.value.apiPagamento.showById(convertPaymentId)
                if (res.isSuccessful) {
                    _planoUiState.update { cs ->
                        cs.copy(
                            paymentCreationObject = res.body(),
                            paymentComplete = if (res.body()?.status == "approved") true else if (convertExpireDatePayment(
                                    res.body()!!.dateOfExpiration
                                )!!.isAfter(LocalDateTime.now())
                            ) null else false
                        )
                    }
                    Log.e("PlanoViewModel", "Sucesso ao buscar o pagamento: ${res.body()}")
                } else {
                    Log.e("PlanoViewModel", "Erro ao buscar o pagamento: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                //Log.e("PlanoViewModel", "Erro na API para buscar o pagamento: ${e.message}")
                throw ApiException("Buscar pagamento", e.message)
            }
        }
    }

    private fun convertExpireDatePayment(date: String): LocalDateTime? {
        val dateConverted =
            ZonedDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDateTime()
        return dateConverted
    }

    private fun convertBase64ToBitmap(base64: String?): Bitmap? {
        return try {
            val decodedString = Base64.decode(base64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        } catch (e: IllegalArgumentException) {
            Log.e(
                "PlanoViewlModel",
                "Erro na conversão de base64 para binário: String da Base64 inválida!"
            )
            null
        } catch (e: Exception) {
            Log.e("PlanoViewlModel", "Erro na conversão de base64 para binário: ${e.message}")
            throw e
        }
    }
}