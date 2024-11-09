package com.example.vitalisapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitalisapp.DTO.Midia.MidiaExibitionDto
import com.example.vitalisapp.DTO.Refeicao.AlimentoPorRefeicaoDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetalheRefeicaoUiState(
    val id: Int? = null,
    val nome: String? = null,
    val preparo: String? = null,

    val midias: MutableList<MidiaExibitionDto>? = mutableListOf(),
    val alimentos: MutableList<AlimentoPorRefeicaoDto>? = null
){  }

class DetalheRefeicaoViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _detalheRefeicaoUiState = MutableStateFlow(DetalheRefeicaoUiState())
    val detalheRefeicaoUiState = _detalheRefeicaoUiState.asStateFlow()

    init {  }

    private fun getRecipy(idRefeicao: Int) {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                throw ApiException("Busca da receita", e.message)
            }
        }
    }
}