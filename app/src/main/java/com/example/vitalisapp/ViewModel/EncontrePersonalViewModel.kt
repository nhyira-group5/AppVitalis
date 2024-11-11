package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Usuario.PersonalExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EncontrePersonalUiState(
    val isLoading: Boolean = true,
    val trainers: MutableList<PersonalExibitionDto>? = mutableListOf()
)

class EncontrePersonalViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _encontrePersonalUiState = MutableStateFlow(EncontrePersonalUiState())
    val encontrePersonalUiState = _encontrePersonalUiState.asStateFlow()

    init {
        getTrainersByMeta(1)    // Colocar a meta buscadoa a partir do login do usuário
        _encontrePersonalUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun getTrainersByMeta(idMeta: Int) {
        viewModelScope.launch {
            try {
                //val res = globalUiState.value.apiUsuario.showTrainers()
                val res = globalUiState.value.apiUsuario.showTrainersByMetaId(idMeta)
                if (res.isSuccessful) {
                    val trainers = res.body()
                    _encontrePersonalUiState.update { cs -> cs.copy(trainers = trainers) }
                    Log.i("EncontrePersonalViewModel" , "Sucesso ao buscar personais por meta: ${res.body()}")
                } else {
                    Log.e("EncontrePersonalViewModel" , "Erro ao buscar personais por meta: ${res.errorBody().toString()}")
                }
            } catch (e: Exception) {
                throw ApiException("Buscar personais por meta", e.message)
            }
        }
    }
}