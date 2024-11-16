package com.example.vitalisapp.ViewModel

import PaymentResponse
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import com.example.vitalisapp.DTO.Pagamento.PaymentKoinDto
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
    val paymentCreationObject: PaymentResponse? = null,
    val paymentObject: PaymentResponse? = null,

    val paymentComplete: Boolean? = null,
    val dateExpirePayment: LocalDateTime? = null,
    val paymentId: Long? = null,
    val qrCodeBitmap: Bitmap? = null
)

class PlanoViewModel() : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _planoUiState = MutableStateFlow(PlanoUiState())
    val planoUiState = _planoUiState.asStateFlow()

    fun createPayment(paymentDto: PagamentoCreateEditDto, paymentKoin: PaymentKoinDto) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiPagamento.createPayment(paymentDto)
                if (res.isSuccessful) {
                    val payment = res.body()
                    val qrCodeConverted =
                        convertBase64ToBitmap(payment?.pointOfInteraction?.transactionData?.qrCodeBase64)
                    val dataExpirationConverted =
                        convertExpireDatePayment(payment?.dateOfExpiration!!)
                    _planoUiState.update { cs ->
                        cs.copy(
                            paymentCreationObject = payment,
                            paymentId = payment.id,
                            qrCodeBitmap = qrCodeConverted,
                            dateExpirePayment = dataExpirationConverted,
                            paymentComplete = false
                        )
                    }
                    paymentKoin.paymentId = payment.id
                    paymentKoin.paymentComplete = false
                    paymentKoin.dateExpirePayment = dataExpirationConverted
                    paymentKoin.qrCodeBitmap = qrCodeConverted
                    Log.i(
                        "PlanoViewModel",
                        "Sucesso ao criar o pagamento: ${planoUiState.value.paymentCreationObject}"
                    )
                } else {
                    Log.e(
                        "PlanoViewModel",
                        "Erro ao criar o pagamento: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                throw ApiException("Criar pagamento", e.message)
            }
        }
    }

    fun getPayment(paymentId: Long, paymentKoin: PaymentKoinDto) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiPagamento.showById(paymentId.toString())
                if (res.isSuccessful) {
                    val payment = res.body()
                    val validationData = payment?.dateOfExpiration?.let {
                        convertExpireDatePayment(it)?.isAfter(LocalDateTime.now())
                    } ?: false
                    val status = when {
                        payment?.status == "approved" -> true
                        validationData -> null
                        else -> false
                    }
                    _planoUiState.update { cs ->
                        cs.copy(
                            paymentObject = payment,
                            paymentComplete = status
                        )
                    }
                    paymentKoin.paymentComplete = status
                    Log.i("PlanoViewModel", "Sucesso ao buscar o pagamento: $payment")
                } else {
                    Log.e("PlanoViewModel", "Erro ao buscar o pagamento: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("PlanoViewModel", "Erro ao obter pagamento: ${e.message}")
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