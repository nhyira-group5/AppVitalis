package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.DTO.Refeicao.AlimentoPorRefeicaoDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetalheRefeicaoUiState(
    val id: Int? = null,
    val nome: String? = null,
    val preparo: String? = null,
    val midias: MutableList<MidiaDto>? = mutableListOf(),
    val alimentos: MutableList<AlimentoPorRefeicaoDto>? = null,

    val isLoading: Boolean = false
) {}

class DetalheRefeicaoViewModel (
    private val idRefeicao: Int
) : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _detalheRefeicaoUiState = MutableStateFlow(DetalheRefeicaoUiState())
    val detalheRefeicaoUiState = _detalheRefeicaoUiState.asStateFlow()

    init {

        getRecipe(idRefeicao)
        _detalheRefeicaoUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun getRecipe(idRefeicao: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiRefeicao.showById(idRefeicao)
                if (res.isSuccessful) {
                    _detalheRefeicaoUiState.update { cs ->
                        cs.copy(
                            id = res.body()?.idRefeicao,
                            nome = res.body()?.nome,
                            preparo = res.body()?.preparo,
                            midias = res.body()?.midias,
                            alimentos = res.body()?.alimentos
                        )
                    }
                    Log.i(
                        "DetalheRefeicaoViewModel",
                        "Sucesso na busca da receita: ${res.body()}"
                    )
                } else {
                    Log.e(
                        "DetalheRefeicaoViewModel",
                        "Erro na busca da receita: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                throw ApiException("Busca da receita", e.message)
            }
        }
    }
}