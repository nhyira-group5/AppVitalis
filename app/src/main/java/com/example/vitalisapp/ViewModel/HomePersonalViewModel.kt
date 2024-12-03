package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Contrato.ContratoExibitionDTO
import com.example.vitalisapp.DTO.Contrato.UpdateContratoRequest
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.View.Contrato
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.View.Usuario.UsuarioGet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class HomePersonalUiState (
    var afiliados: List<UsuarioGet>? = null,
    var contratos: List<ContratoExibitionDTO>? = null
) {}

class HomePersonalViewModel : ViewModel(){
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _homePersonalUiState = MutableStateFlow(HomePersonalUiState())
    val homePersonalUiState = _homePersonalUiState.asStateFlow()

    init {
        loadDataHomePersonal(SessionLogin.id!!)
    }

    private fun loadDataHomePersonal(idUsuario: Int) {
        setAfiliados(idUsuario)
        setContratos(idUsuario)
    }

    private fun setAfiliados(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiUsuario.getAfiliadosPersonalById(idUsuario);

                if (res.isSuccessful) {
                    val afiliados = res.body()
                    if(afiliados != null){
                        _homePersonalUiState.update { cs ->
                            cs.copy(
                                afiliados = res.body()
                            )
                        }
                        Log.i("HomePersonalViewModel", "Usuarios afiliados do usuario encontrado: ${res.body()}")
                    } else{
                        Log.e("HomePersonalViewModel", "Resposta bem-sucedida, mas o corpo é nulo.")
                    }
               } else {
                    Log.e(
                        "HomePersonalViewModel",
                        "Erro ao buscar afiliados do usuario: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "HomePersonalViewModel",
                    "Erro na HomePersonalViewModel ao buscar a afiliados do usuario: ${e.message}"
                )
                throw ApiException("Busca de afiliados do usuario", e.message)
            }
        }
    }

    private fun setContratos(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiContrato.showByPersonalId(idUsuario)

                if (res.isSuccessful) {
                    val contratos = res.body()
                    if (contratos != null) {
                        val contratosDTO = contratos.map { contratoToDTO(it) }
                        _homePersonalUiState.update { cs ->
                            cs.copy(
                                contratos = contratosDTO
                            )
                        }
                        Log.i("HomePersonalViewModel", "Contratos encontrados: $contratosDTO")
                    } else {
                        Log.e("HomePersonalViewModel", "Resposta bem-sucedida, mas o corpo é nulo.")
                    }
                } else {
                    Log.e(
                        "HomePersonalViewModel",
                        "Erro ao buscar contratos: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "HomePersonalViewModel",
                    "Erro na HomePersonalViewModel ao buscar contratos: ${e.message}"
                )
                throw ApiException("Busca de contratos", e.message)
            }
        }
    }



    fun updateContratoAfiliado(idContrato: Int, afiliado: Int) {
        viewModelScope.launch {
            try {
                val fimContrato = LocalDate.now().plusMonths(1).toString()  // Calcula a data de fim
                val updateRequest = UpdateContratoRequest(fimContrato, afiliado)
                val res = globalUiState.value.apiContrato.updateContratoAfiliado(idContrato, updateRequest)

                if (res.isSuccessful) {
                    Log.i("ViewModelHomePersoal", "Contrato atualizado com sucesso.")

                    setAfiliados(SessionLogin.id!!)
                    setContratos(SessionLogin.id!!)

                } else {
                    Log.e("ViewModel", "Erro ao atualizar contrato: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Erro ao atualizar contrato: ${e.message}")
            }
        }
    }


    private fun contratoToDTO(contrato: Contrato): ContratoExibitionDTO {
        return ContratoExibitionDTO(
            idContrato = contrato.idContrato,
            usuarioNome = contrato.usuarioId.nome,
            usuarioNickname = contrato.usuarioId.nickname,
            afiliacao = contrato.afiliacao
        )
    }
}