package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import com.example.vitalisapp.DTO.RotinaMensal.RotinaMensalExibitionDto
import com.example.vitalisapp.DTO.RotinaSemanal.RotinaSemanalExibitionDto
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioExibitionDto
import com.example.vitalisapp.DTO.Treino.TreinoExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.View.LoginSession.SessionLogin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class ListaExerciciosUiState(
    var rotinaUsuario: RotinaUsuarioExibitionDto? = null,
    var rotinaMensal: RotinaMensalExibitionDto? = null,
    var rotinaSemanal: RotinaSemanalExibitionDto? = null,
    var rotinaDiaria: RotinaDiariaExibitionDto? = null,
    var treinosDiarios: MutableList<TreinoExibitionDto> = mutableListOf(),
    var isLoading: Boolean = true
)

class ListaExercicioViewModel : ViewModel(){
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _listaExercicioUiState = MutableStateFlow(HomeUiState())
    val listaExercicioUiState = _listaExercicioUiState.asStateFlow()

    init {
        loadDataHome(SessionLogin.id!!)
        _listaExercicioUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun loadDataHome(idUsuario: Int) {
        setUserRoutineByUserId(idUsuario)
        setMonthRoutineByUserIdAndMonth(idUsuario)
        setWeekRoutine(idUsuario)
    }

    private fun setUserRoutineByUserId(idUsuario: Int) {
        viewModelScope.launch {
            _listaExercicioUiState.update { cs -> cs.copy(isLoading = true) }
            try {
                val res = globalUiState.value.apiRotinaUsuario.showByUserId(idUsuario)
                if (res.isSuccessful) {
                    _listaExercicioUiState.update { cs ->
                        cs.copy(
                            rotinaUsuario = res.body(),
                            isLoading = false
                        )
                    }
                    Log.i("HomeViewModel", "Rotina de usuario encontrada: ${res.body()}")
                } else {
                    _listaExercicioUiState.update { cs ->
                        cs.copy(
                            isLoading = false
                        )
                    }
                    Log.e(

                        "HomeViewModel",
                        "Erro ao buscar a rotina do usuario: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                _listaExercicioUiState.update { cs ->
                    cs.copy(
                        isLoading = false
                    )
                }

                Log.e(
                    "HomeViewModel",
                    "Erro na HomeViewModel ao buscar a rotina do usuario: ${e.message}"
                )
                throw ApiException("Busca da rotina do usuário", e.message)
            }
        }
    }
    private fun setMonthRoutineByUserIdAndMonth(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res =
                    globalUiState.value.apiRotinaMensal.showByUserIdAndMonth(
                        idUsuario,
                        LocalDate.now().monthValue
                    )
                if (res.isSuccessful) {
                    _listaExercicioUiState.update { cs ->
                        cs.copy(rotinaMensal = res.body())
                    }
                    Log.i("HomeViewModel", "Rotina de mensal encontrada: ${res.body()}")
                } else {
                    Log.e(
                        "HomeViewModel",
                        "Erro ao buscar a rotina mensal: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "HomeViewModel",
                    "Erro na HomeViewModel ao buscar a rotina mensal: ${e.message}"
                )
                throw ApiException("Buscar rotina mensal", e.message)
            }
        }
    }
    private fun setWeekRoutine(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res =
                    globalUiState.value.apiRotinaSemanal.showCurrentWeekRoutineByUserId(idUsuario)
                if (res.isSuccessful) {
                    _listaExercicioUiState.update { cs ->
                        cs.copy(rotinaSemanal = res.body())
                    }
                    Log.i(
                        "HomeViewModel",
                        "Rotina semanal encontrada: ${listaExercicioUiState.value.rotinaSemanal}"
                    )

                    setDailyRoutine(listaExercicioUiState.value.rotinaSemanal?.idRotinaSemanal)
                } else {
                    Log.e(
                        "HomeViewModel",
                        "Erro ao buscar a rotina semanal atual: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "HomeViewModel",
                    "Erro na HomeViewModel ao buscar a rotina semanal atual: ${e.message}"
                )
                throw ApiException("Buscar rotina semanal atual", e.message)
            }
        }
    }

    private fun setDailyRoutine(idRotinaSemanal: Int?) {
        viewModelScope.launch {
            try {
                if (idRotinaSemanal != null) {
                    val res =
                        globalUiState.value.apiRotinaDiaria.showCurrentDailyRoutine(idRotinaSemanal)
                    if (res.isSuccessful) {
                        val rotinaDiaria = res.body()
                        if (rotinaDiaria != null) {
                            _listaExercicioUiState.update { cs ->
                                cs.copy(rotinaDiaria = rotinaDiaria)
                            }
                            Log.i(
                                "HomeViewModel",
                                "Rotina diaria encontrada: ${listaExercicioUiState.value.rotinaDiaria}"
                            )
                            loadDailyActivities(rotinaDiaria.idRotinaDiaria)

                        } else {
                            Log.w(
                                "HomeViewModel",
                                "Nenhuma rotina diaria correspondente para o dia atual"
                            )
                        }
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Erro ao buscar rotina diaria: ${res.errorBody()?.string()}"
                        )
                    }
                } else {
                    Log.e("HomeViewModel", "Erro ao buscar rotina diaria: idRotinaSemanal é null")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Erro ao buscar rotina diaria: ${e.message}", e)
            }
        }
    }
    private fun loadDailyActivities(idRotinaDiaria: Int?) {
        if (idRotinaDiaria != null) {
            setTrainingsFromDailyRoutine(idRotinaDiaria)

        } else {
            Log.e("HomeViewModel", "Erro ao buscar atividades diarias: idRotinaDiaria Null")
        }
    }
    private fun setTrainingsFromDailyRoutine(idRotinaDiaria: Int?) {
        viewModelScope.launch {
            try {
                if (idRotinaDiaria != null) {
                    val res = globalUiState.value.apiTreino.showByRotinaDiaria(idRotinaDiaria)
                    if (res.isSuccessful) {
                        _listaExercicioUiState.update { cs ->
                            _listaExercicioUiState.value.treinosDiarios.clear()
                            cs.copy(treinosDiarios = res.body()!!.toMutableList())
                        }
                        Log.i(
                            "HomeViewModel",
                            "Quantidade de treinos buscados para a rotina diaria: ${listaExercicioUiState.value.treinosDiarios.size}"
                        )
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Erro ao buscar treinos diarios: ${res.errorBody().toString()}"
                        )
                    }
                } else {
                    Log.e("HomeViewModel", "Erro ao buscar a treinos diarios: idRotinaDiaria Null")
                }
            } catch (e: Exception) {
                Log.e(
                    "HomeViewModel",
                    "Erro na HomeViewModel para buscar os treinos da rotina diaria: ${e.message}"
                )
            }
        }
    }

}