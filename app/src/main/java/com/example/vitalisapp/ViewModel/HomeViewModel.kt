package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.DTO.Refeicao.RefeicaoExibitionDto
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

// Ui State
data class HomeUiState(
    var rotinaUsuario: RotinaUsuarioExibitionDto? = null,
    var rotinaMensal: RotinaMensalExibitionDto? = null,
    var rotinaSemanal: RotinaSemanalExibitionDto? = null,
    var rotinaDiaria: RotinaDiariaExibitionDto? = null,
    var treinosDiarios: MutableList<TreinoExibitionDto> = mutableListOf(),
    var refeicoesDiarias: MutableList<RefeicaoExibitionDto> = mutableListOf(),
    var refeicoesConcluidasDiaria: Int = 0,
    var refeicoesTotaisDiaria: Int = 0,
    var treinosConcluidosDiaria: Int = 0,
    var treinosTotaisDiaria: Int = 0,
    var rotinasDiariasConcluidasSemana: Int = 0,
    var rotinasDiariasTotaisSemana: Int = 0,
    var isLoading: Boolean = true


    // Caso tivesse um método alterando o estado na Activity
    // Ele teria que ser implementado por aqui também e ser iniciado (INIT) na ViewModel

    // Exemplo: Metodo que conclui um treino que seria usado na activity
    // Pesquise como implementar ai (Implementar metodos UI State)
    // onCompleteTreino: (param) -> Unit = {}
) {
    fun getQuantityCompletedTrainingsForDay(): Int {
        return treinosDiarios.count { it.concluido == 1 }
    }

    fun getQuantityTrainingsForDay(): Int {
        return treinosDiarios.size
    }

    // Feitas com a Rotina diaria pq as refeicao estão salvas como DTOs de Refeição (Sem o campo "Concluido")
    fun getQuantityCompletedMealsForDay(): Int {
        return rotinaDiaria?.refeicaoDiaria?.count { it.concluido == 1 } ?: 0
    }

    fun getQuantityMealsForDay(): Int {
        return rotinaDiaria?.refeicaoDiaria?.size ?: 0
    }
}

// View Model
class HomeViewModel : ViewModel() {
    private val globalUiState = MutableStateFlow(GlobalUiState())

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadDataHome(SessionLogin.id!!)
        _homeUiState.update { cs -> cs.copy(isLoading = false) }
    }

    private fun loadDataHome(idUsuario: Int) {
        setUserRoutineByUserId(idUsuario)
        setMonthRoutineByUserIdAndMonth(idUsuario)
        setWeekRoutine(idUsuario)
    }

    private suspend fun loadKpiSemanalData(idRotinaSemanal: Int?) {
        if (idRotinaSemanal != null) {
            _homeUiState.update { cs ->
                cs.copy(
                    rotinasDiariasConcluidasSemana = getQuantityCompletedDailyRoutinesForWeek(
                        homeUiState.value.rotinaSemanal?.idRotinaSemanal
                    )
                )
            }
            Log.i(
                "HomeViewModel",
                "Rotinas diarias concluidas na semana: ${homeUiState.value.rotinasDiariasConcluidasSemana}"
            )

            _homeUiState.update { cs ->
                cs.copy(
                    rotinasDiariasTotaisSemana = getQuantityDailyRoutinesForWeek(
                        homeUiState.value.rotinaSemanal?.idRotinaSemanal
                    )
                )
            }
            Log.i(
                "HomeViewModel",
                "Rotinas diarias totais na semana: ${homeUiState.value.rotinasDiariasTotaisSemana}"
            )
        } else {
            Log.e("HomeViewModel", "Erro ao buscar dados aos KPIs semanais: idRotinaSemanal Null")
        }
    }

    private fun loadKpiDiarioData(idRotinaDiaria: Int?) {
        if (idRotinaDiaria != null) {
            _homeUiState.update { cs ->
                cs.copy(
                    refeicoesConcluidasDiaria = homeUiState.value.getQuantityCompletedMealsForDay(),
                    refeicoesTotaisDiaria = homeUiState.value.getQuantityMealsForDay(),
                    treinosConcluidosDiaria = homeUiState.value.getQuantityCompletedTrainingsForDay(),
                    treinosTotaisDiaria = homeUiState.value.getQuantityTrainingsForDay()
                )
            }
            Log.i(
                "HomeViewModel",
                "Treinos totais no dia: ${homeUiState.value.treinosTotaisDiaria}"
            )
        } else {
            Log.e("HomeViewModel", "Erro ao buscar dados aos KPIs semanais: idRotinaDiaria Null")
        }
    }

    private fun loadDailyActivities(idRotinaDiaria: Int?) {
        if (idRotinaDiaria != null) {
            setTrainingsFromDailyRoutine(idRotinaDiaria)
            setMealsFromDailyRoutine(idRotinaDiaria)
        } else {
            Log.e("HomeViewModel", "Erro ao buscar atividades diarias: idRotinaDiaria Null")
        }
    }

    private fun setUserRoutineByUserId(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res = globalUiState.value.apiRotinaUsuario.showByUserId(idUsuario)
                if (res.isSuccessful) {
                    _homeUiState.update { cs ->
                        cs.copy(
                            rotinaUsuario = res.body()
                        )
                    }
                    Log.i("HomeViewModel", "Rotina de usuario encontrada: ${res.body()}")
                } else {
                    Log.e(
                        "HomeViewModel",
                        "Erro ao buscar a rotina do usuario: ${res.errorBody().toString()}"
                    )
                }
            } catch (e: Exception) {
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
                    _homeUiState.update { cs ->
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
                    _homeUiState.update { cs ->
                        cs.copy(rotinaSemanal = res.body())
                    }
                    Log.i(
                        "HomeViewModel",
                        "Rotina semanal encontrada: ${homeUiState.value.rotinaSemanal}"
                    )

                    // Chamando essas bosta aqui pq nn vai no init nn sei pq
                    loadKpiSemanalData(homeUiState.value.rotinaSemanal?.idRotinaSemanal)
                    setDailyRoutine(homeUiState.value.rotinaSemanal?.idRotinaSemanal)
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
                            _homeUiState.update { cs ->
                                cs.copy(rotinaDiaria = rotinaDiaria)
                            }
                            Log.i(
                                "HomeViewModel",
                                "Rotina diaria encontrada: ${homeUiState.value.rotinaDiaria}"
                            )

                            loadDailyActivities(rotinaDiaria.idRotinaDiaria)
                            loadKpiDiarioData(rotinaDiaria.idRotinaDiaria)
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

    private fun setTrainingsFromDailyRoutine(idRotinaDiaria: Int?) {
        viewModelScope.launch {
            try {
                if (idRotinaDiaria != null) {
                    val res = globalUiState.value.apiTreino.showByRotinaDiaria(idRotinaDiaria)
                    if (res.isSuccessful) {
                        _homeUiState.update { cs ->
                            _homeUiState.value.treinosDiarios.clear()
                            cs.copy(treinosDiarios = res.body()!!.toMutableList())
                        }
                        Log.i(
                            "HomeViewModel",
                            "Quantidade de treinos buscados para a rotina diaria: ${homeUiState.value.treinosDiarios.size}"
                        )
                        loadKpiDiarioData(idRotinaDiaria)
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

    private fun setMealsFromDailyRoutine(idRotinaDiaria: Int?) {
        if (idRotinaDiaria != null) {
            viewModelScope.launch {
                try {
                    val res = globalUiState.value.apiRefeicao.showByidRotinaDiaria(idRotinaDiaria)
                    if (res.isSuccessful) {
                        _homeUiState.update { cs ->
                            _homeUiState.value.refeicoesDiarias.clear()
                            cs.copy(refeicoesDiarias = res.body()!!.toMutableList())
                        }
                        Log.i(
                            "HomeViewModel",
                            "Quantidade de refeicao buscadas para a rotina diaria: ${homeUiState.value.refeicoesDiarias.size}"
                        )
                        //Log.i("HomeViewModel", "Refeicoes atribuídas a rotina diaria: ${homeUiState.value.refeicoesDiarias}")
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Erro para buscar a refeicao de ID $idRotinaDiaria atribuida a rotina diaria: ${
                                res.errorBody().toString()
                            }"
                        )
                    }
                } catch (e: Exception) {
                    Log.e(
                        "HomeViewModel",
                        "Erro na HomeViewModel para buscar a refeicao de ID $idRotinaDiaria atribuida a rotina diaria: ${e.message}"
                    )
                }
            }
        } else {
            Log.e("HomeViewModel", "Erro ao buscar refeicoes diarias: idRotinaDiaria Null")
        }
    }

    suspend fun getQuantityDailyRoutinesForWeek(idRotinaSemanal: Int?): Int {
        if (idRotinaSemanal == null) {
            Log.e(
                "HomeViewModel",
                "Erro ao buscar quantidade de rotinas diarias totais na semana: idRotinaSemanal Null"
            )
            return 0
        }

        return try {
            val res = globalUiState.value.apiRotinaSemanal.showById(idRotinaSemanal)
            if (res.isSuccessful) {
                res.body()?.rotinasDiarias?.size ?: 0
            } else {
                Log.e(
                    "HomeViewModel",
                    "Erro para buscar a rotina semanal: ${res.errorBody().toString()}"
                )
                0
            }
        } catch (e: Exception) {
            Log.e(
                "HomeViewModel",
                "Erro na HomeViewModel para buscar a quantidade de rotinas diaria na semana: ${e.message}"
            )
            0
        }
    }

    suspend fun getQuantityCompletedDailyRoutinesForWeek(idRotinaSemanal: Int?): Int {
        if (idRotinaSemanal == null) {
            Log.e(
                "HomeViewModel",
                "Erro ao buscar quantidade de rotinas diarias completas na semana: idRotinaSemanal Null"
            )
            return 0
        }

        return try {
            val res =
                globalUiState.value.apiRotinaSemanal.showCompletedDailyRoutinesByRotinaSemanalId(
                    idRotinaSemanal
                )
            if (res.isSuccessful && res.body() != null) {
                res.body() ?: 0
            } else {
                Log.e(
                    "HomeViewModel",
                    "Erro para buscar a quantidade de rotinas diarias concluídas: ${
                        res.errorBody().toString()
                    }"
                )
                0
            }
        } catch (e: Exception) {
            Log.e(
                "HomeViewModel",
                "Erro na HomeViewModel para buscar a quantidade de rotinas diarias concluídas: ${e.message}"
            )
            0
        }
    }
}
