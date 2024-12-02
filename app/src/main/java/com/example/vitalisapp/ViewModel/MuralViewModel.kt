package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.vitalisapp.DTO.Mural.UsuarioDto
import com.example.vitalisapp.DTO.Mural.MidiaDto
import com.example.vitalisapp.DTO.Mural.MuralExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.View.LoginSession.SessionLogin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MuralUiState(
    val muralItems: List<MuralExibitionDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)


class MuralViewModel() : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _muralUiState = MutableStateFlow(MuralUiState())
    val muralUiState = _muralUiState.asStateFlow()

    init {
        getMuralItens(SessionLogin.id!!)
    }

    private fun getMuralItens(idUsuario: Int) {
        viewModelScope.launch {
            _muralUiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val res = globalUiState.value.apiMural.showByUsuarioId(idUsuario)

                if (res.isSuccessful) {

                    val muralItems = res.body() as? List<MuralExibitionDto> ?: emptyList()

                    _muralUiState.update { currentState ->
                        currentState.copy(
                            muralItems = muralItems,
                            isLoading = false,
                            errorMessage = null
                        )
                    }

                    Log.i("MuralViewModel", "Sucesso na busca do mural: $muralItems")
                } else {
                    _muralUiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = "Erro: ${res.errorBody().toString()}"
                        )
                    }

                    Log.e("MuralViewModel", "Erro na busca do mural: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                _muralUiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessage = "Erro inesperado: ${e.message}"
                    )
                }
                Log.e("MuralViewModel", "Erro inesperado: ${e.message}")
                throw ApiException("Erro ao buscar mural", e.message)
            }
        }
    }
}

