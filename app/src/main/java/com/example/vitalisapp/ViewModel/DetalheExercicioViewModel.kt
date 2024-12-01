package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Exercicio.ExercicioExibitionDto
import com.example.vitalisapp.DTO.Exercicio.TagDto
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.DTO.Refeicao.AlimentoPorRefeicaoDto
import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetalheExercicioUiState(
    val id: Int? = null,
    val exercicio: ExercicioExibitionDto? = null,
    val rotinaDiaria: RotinaDiariaExibitionDto? = null,
    val serie: Int? = null,
    val repeticao: Int? = null,
    val tempo: String? = null,
    val concluido: Int? = null,

    val isLoading: Boolean = false
) {}

class DetalheExercicioViewModel(
    private val idTreino: Int
) : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _detalheExercicioUiState = MutableStateFlow(DetalheExercicioUiState())
    val detalheExercicioUiState = _detalheExercicioUiState.asStateFlow()

    init {
        getTreino(idTreino)
        _detalheExercicioUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun getTreino(idTreino: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiTreino.showById(idTreino)
                if (res.isSuccessful) {
                    val treino = res.body()

                    _detalheExercicioUiState.update { cs ->
                        cs.copy(
                            id = treino?.idTreino,
                            exercicio = treino?.exercicio,
                            rotinaDiaria = treino?.rotinaDiaria,
                            serie = treino?.serie,
                            repeticao = treino?.repeticao,
                            tempo = treino?.tempo,
                            concluido = treino?.concluido
                        )
                    }

                    Log.i(
                        "DetalheExercicioViewModel",
                        "Sucesso na busca do treino: ${res.body()}"
                    )
                } else {
                    Log.e(
                        "DetalheExercicioViewModel",
                        "Erro na busca do treino: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                throw ApiException("Busca do treino", e.message)
            }
        }
    }


}