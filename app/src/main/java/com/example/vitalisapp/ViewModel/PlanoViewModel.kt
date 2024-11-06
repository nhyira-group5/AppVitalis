package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import com.example.vitalisapp.DTO.Pagamento.PaymentResponse
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class PlanoUiState(
    var isPagando: Boolean = false,
    var paymentCreationObject: PaymentResponse? = null,
    var paymentObject: PaymentResponse? = null,

    var paymentComplete: Boolean? = null,
    var dateExpirePayment: String? = null,

    // Colocando aqui para facilitar a vida, já que retorna um Object da API
    var qrCodeBase64: String? = null
){  }

class PlanoViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _planoUiState = MutableStateFlow(PlanoUiState())
    val planoUiState = _planoUiState.asStateFlow()

    init {  }

    fun createPayment(paymentDto: PagamentoCreateEditDto) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiPagamento.createPayment(paymentDto)
                if (res.isSuccessful) {
                    val jsonString = res.body().toString()
                    _planoUiState.update { cs ->
                        cs.copy(
                            isPagando = true,
                            paymentCreationObject = res.body(),
                            qrCodeBase64 = extractQRCodePayment(jsonString),
                            paymentComplete = false
                        )
                    }
                    Log.e("PlanoViewModel", "Sucesso ao criar o pagamento: ${res.body()}")
                } else {
                    Log.e("PlanoViewModel", "Erro ao criar o pagamento: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                //Log.e("PlanoViewModel", "Erro na API para criar o pagamento: ${e.message}")
                throw ApiException("Criar pagamento", e.message)
            }
        }
    }

    fun getPayment(paymentId: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiPagamento.showById(paymentId)
                if (res.isSuccessful) {
                    val jsonString = res.body().toString()
                    _planoUiState.update { cs ->
                        cs.copy(
                            paymentCreationObject = res.body(),
                            paymentComplete = if (extractStatusPayment(jsonString) == "approved") true else if (extractExpireDatePayment(jsonString)!!.isAfter(LocalDateTime.now())) null else false
                        )
                    }
                    Log.e("PlanoViewModel", "Sucesso ao buscar o pagamento: ${res.body()}")
                } else {
                    Log.e("PlanoViewModel", "Erro ao buscar o pagamento: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                //Log.e("PlanoViewModel", "Erro na API para buscar o pagamento: ${e.message}")
                throw ApiException("Buscar pagamento", e.message)
            }
        }
    }

    private fun extractQRCodePayment(jsonString: String): String? {
        val jsonObject = JSONObject(jsonString)
        val pointOfInteraction = jsonObject.optJSONObject("point_of_interaction")
        val transactionData = pointOfInteraction?.optJSONObject("transaction_data")
        return transactionData?.optString("qr_code_base64")
    }

    private fun extractStatusPayment(jsonString: String): String? {
        val jsonObject = JSONObject(jsonString)
        return jsonObject.optString("status")
    }

    private fun extractExpireDatePayment(jsonString: String): LocalDateTime? {
        val jsonObject = JSONObject(jsonString)
        val expireDate = jsonObject.optString("date_of_expiration")
        val dateConverted = ZonedDateTime.parse(expireDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDateTime()
        return dateConverted
    }
}