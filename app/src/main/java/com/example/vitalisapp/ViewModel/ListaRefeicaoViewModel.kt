package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Refeicao.RefeicaoExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.View.LoginSession.SessionLogin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListaRefeicaoUiState(
    val metaIdUser: Int = -1,
    val refeicoes: MutableList<RefeicaoExibitionDto>? = mutableListOf(),
    val isLoading: Boolean = true
) {}

class ListaRefeicaoViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _listaRefeicaoUiState = MutableStateFlow(ListaRefeicaoUiState())
    val listaRefeicaoUiState = _listaRefeicaoUiState.asStateFlow()

    init {
        getRefeicoesByMetaId(SessionLogin.meta!!)
        _listaRefeicaoUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun getRefeicoesByMetaId(idMeta: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiRefeicao.showByMeta(idMeta)
                if (res.isSuccessful) {
                    val refeicoes = res.body()
                    _listaRefeicaoUiState.update { cs -> cs.copy(refeicoes = res.body(), metaIdUser = idMeta) }
                    Log.i("ListaRefeicaoViewModel", "Sucesso ao buscar refeicoes pela meta: $refeicoes")
                } else {
                    Log.e("ListaRefeicaoViewModel", "Erro ao buscar refeicoes pela meta: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                throw ApiException("Busca de refeicoes por meta", e.message)
            }
        }
    }

    fun getRefeicoesNameFilter(search: String) {
        viewModelScope.launch {
            try {
                val idMeta = listaRefeicaoUiState.value.metaIdUser
                if (idMeta > 0) {
                    //val res = globalUiState.value.apiRefeicao.showByNome(search)
                    val res = globalUiState.value.apiRefeicao.showByMetaAndName(idMeta, search)
                    if (res.isSuccessful) {
                        val refeicoes = res.body()
                        _listaRefeicaoUiState.update { cs -> cs.copy(refeicoes = refeicoes) }
                        Log.i("ListaRefeicaoViewModel", "Sucesso ao buscar refeicoes por filtro de nome: $refeicoes")
                    } else {
                        Log.e("ListaRefeicaoViewModel", "Erro ao buscar refeicoes por filtro de nome: ${res.errorBody().toString()}")
                    }
                } else {
                    Log.e("ListaRefeicaoViewModel", "Erro ao buscar refeicoes por filtro de nome: Meta do usuário não identificada")
                }
            } catch (e: Exception) {
                throw ApiException("Busca de refeicoes por filtro de nome", e.message)
            }
        }
    }
}