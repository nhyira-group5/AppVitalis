package com.example.vitalisapp.Service

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
import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.Interface.ApiTreino
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    // Chamando as RetroServices
    private val apiRotinaUsuario: ApiRotinaUsuario
    private val apiRotinaSemanal: ApiRotinaSemanal
    private val apiRotinaMensal: ApiRotinaMensal
    private val apiRotinaDiaria: ApiRotinaDiaria
    private val apiRefeicao: ApiRefeicao
    private val apiTreino: ApiTreino

    // ----------------------- Declaração de Itens e suas cópias modificáveis ----------------------- //
    private var _rotinaUsuario = MutableStateFlow<RotinaUsuarioExibitionDto?>(null)       // Atributo modificável na View
    val rotinaUsuario = _rotinaUsuario.asStateFlow()                                             // Atributo que vai ser EXPOSTO externamente (Cópia do modificável)

    private var _rotinaMensal = MutableStateFlow<RotinaMensalExibitionDto?>(null)
    val rotinaMensal = _rotinaMensal.asStateFlow()

    private var _rotinaSemanal = MutableStateFlow<RotinaSemanalExibitionDto?>(null)
    val rotinaSemanal = _rotinaSemanal.asStateFlow()

    private var _rotinaDiaria = MutableStateFlow<RotinaDiariaExibitionDto?>(null)
    val rotinaDiaria = _rotinaDiaria.asStateFlow()

    private var _treinosDiarios = MutableStateFlow<MutableList<TreinoExibitionDto>>(mutableListOf())
    val treinosDiarios = _treinosDiarios.asStateFlow()

    private var _refeicoesDiarias = MutableStateFlow<MutableList<RefeicaoExibitionDto>>(mutableListOf())
    val refeicoesDiarias = _refeicoesDiarias.asStateFlow()

    private var _refeicoesConcluidasDiaria = MutableStateFlow<Int>(0)
    val refeicoesConcluidasDiaria = _refeicoesConcluidasDiaria.asStateFlow()

    private var _refeicoesTotaisDiaria = MutableStateFlow<Int>(0)
    val refeicoesTotaisDiaria = _refeicoesTotaisDiaria.asStateFlow()

    private var _treinosConcluidosDiaria = MutableStateFlow<Int>(0)
    val treinosConcluidosDiaria = _treinosConcluidosDiaria.asStateFlow()

    private var _treinosTotaisDiaria = MutableStateFlow<Int>(0)
    val treinosTotaisDiaria = _treinosTotaisDiaria.asStateFlow()

    private var _rotinasDiariasConcluidasSemana = MutableStateFlow<Int>(0)
    val rotinasDiariasConcluidasSemana = _rotinasDiariasConcluidasSemana.asStateFlow()

    private var _rotinasDiariasTotaisSemana = MutableStateFlow<Int>(0)
    val rotinasDiariasTotaisSemana = _rotinasDiariasTotaisSemana.asStateFlow()
    // ---------------------------------------------------------------------------------------------- //

    // Inicializando a View Model com as RetroServices
    init {
        apiRotinaDiaria = RetrofitService.getApiRotinaDiaria()
        apiRotinaSemanal = RetrofitService.getApiRotinaSemanal()
        apiRotinaMensal = RetrofitService.getApiRotinaMensal()
        apiRotinaUsuario = RetrofitService.getApiRotinaUsuario()
        apiRefeicao = RetrofitService.getApiRefeicao()
        apiTreino = RetrofitService.getApiTreino()
    }

    fun getUserRoutineByUserId(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res = apiRotinaUsuario.showByUserId(idUsuario)
                if (res.isSuccessful) {
                    _rotinaUsuario.value = res.body()
                    Log.i("API", "Rotina de usuario encontrada: ${res.body()}")
                } else {
                    Log.e("API", "Erro ao buscar a rotina do usuario: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro na API ao buscar a rotina do usuario: ${e.message}")
                throw ApiException("Busca da rotina do usuário", e.message)
            }
        }
    }

    fun getMonthRoutineByUserIdAndMonth(idUsuario: Int) {
        viewModelScope.launch {
            try {
                val res = apiRotinaMensal.showByUserIdAndMonth(idUsuario, LocalDate.now().monthValue)
                if (res.isSuccessful) {
                    _rotinaMensal.value = res.body()
                    Log.i("API", "Rotina de mensal encontrada: ${res.body()}")
                } else {
                    Log.e("API", "Erro ao buscar a rotina mensal: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro na API ao buscar a rotina mensal: ${e.message}")
                throw ApiException("Buscar rotina mensal", e.message)
            }
        }
    }

     fun getWeekRoutine(idUsuario: Int) {
        viewModelScope.launch {
             try {
                 val res = apiRotinaSemanal.showCurrentWeekRoutineByUserId(idUsuario)
                 if (res.isSuccessful) {
                     _rotinaSemanal.value = res.body()
                     Log.i("API","Rotina semanal encontrada: ${rotinaSemanal.value}")
                 } else {
                     Log.e("API","Erro ao buscar a rotina semanal atual: ${res.errorBody()}")
                 }
             } catch (e: Exception) {
                 Log.e("API", "Erro na API ao buscar a rotina semanal atual: ${e.message}")
                 throw ApiException("Buscar rotina semanal atual", e.message)
             }
         }
    }

    fun getDailyRoutine(idRotinaSemanal: Int) {
        viewModelScope.launch {
            try {
                val res = apiRotinaDiaria.showCurrentDailyRoutine(idRotinaSemanal)
                if (res.isSuccessful) {
                    _rotinaDiaria.value = res.body()
                    Log.i("API", "Rotina diaria encontrada: ${rotinaDiaria.value}")
                } else {
                    Log.e("API", "Nenhuma rotina diaria correspondente para o dia da semana atual: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro na API para buscar a rotina diaria atual: ${e.message}")
                throw ApiException("Buscar rotina diaria atual", e.message)
            }
        }
    }

    fun getTrainingsFromDailyRoutine(idRotinaDiaria: Int) {
        viewModelScope.launch {
            try {
                val res = apiTreino.showByRotinaDiariaId(idRotinaDiaria)
                val validationOfSucess = res.isSuccessful && res.body()!!.isNotEmpty()
                if (validationOfSucess) {
                    _treinosDiarios.value.clear()
                    _treinosDiarios.value.addAll(res.body()!!)
                }
                Log.i("API", "Quantidade de treinos buscados para a rotina diária: ${treinosDiarios.value.size}")
                Log.i("API","Treinos buscados para a rotina diária: ${treinosDiarios.value}")
            } catch (e: Exception) {
                Log.e("API","Erro na API para buscar os treinos da rotina diária: ${e.message}")
            }
        }
    }

    fun getMealsFromDailyRoutine(rotinaDiaria: RotinaDiariaExibitionDto) {
        if (rotinaDiaria.refeicaoDiaria.isNotEmpty()) {
            rotinaDiaria.refeicaoDiaria.forEach {       // Mapper é o carai kkkkkkkkkkkkk fodasse
                viewModelScope.launch {
                    try {
                        val res = apiRefeicao.showById(it.idRotinaDiaria)
                        if (res.isSuccessful) _refeicoesDiarias.value.add(res.body()!!) else Log.e("API","Erro para buscar a refeição de ID ${it.idRotinaDiaria} atribuida a rotina diária: ${res.errorBody()}")
                    } catch (e: Exception) {
                        Log.e("API","Erro na API para buscar a refeição de ID ${it.idRotinaDiaria} atribuida a rotina diária: ${e.message}")
                    }
                }
            }
            Log.i("API","Quantidade de refeições atribuídas a rotina diária: ${refeicoesDiarias.value.size}")
            Log.i("API", "Refeições atribuídas a rotina diária: ${refeicoesDiarias.value}")
        }
    }

    fun getQuantityCompletedTrainingsForDay(trainings: MutableList<TreinoExibitionDto>) {
        _treinosConcluidosDiaria.value = trainings.count { it.concluido == 1 }
    }

    fun getQuantityTrainingsForDay(trainings: MutableList<TreinoExibitionDto>) {
        _treinosTotaisDiaria.value = trainings.count { it.concluido <= 1 }
    }

    // Feitas com a Rotina Diária pq as refeições estão salvas como DTOs de Refeição (Sem o campo "Concluido")
    fun getQuantityCompletedMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto?) {
        if (rotinaDiaria != null) _refeicoesConcluidasDiaria.value = rotinaDiaria.refeicaoDiaria.count { it.concluido == 1 }
    }

    fun getQuantityMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto?) {
        if (rotinaDiaria != null) _refeicoesTotaisDiaria.value = rotinaDiaria.refeicaoDiaria.count { it.concluido <= 1 }
    }

    fun getQuantityDailyRoutinesForWeek(idRotinaSemanal: Int) {
        viewModelScope.launch {
            try {
                val res = apiRotinaSemanal.showById(idRotinaSemanal)
                if (res.isSuccessful) repeat(res.body()!!.rotinasDiarias.size) { _rotinasDiariasTotaisSemana.value++ } else Log.e("API", "Erro para buscar a rotina semanal: ${res.errorBody()}")
            } catch (e: Exception) {
                Log.e("API","Erro na API para buscar a quantidade de rotinas diária na semana: ${e.message}")
            }
        }
        Log.i("API","Quantidade de rotinas diárias na semana de ID ${idRotinaSemanal}: ${rotinasDiariasTotaisSemana.value}")
    }

    fun getQuantityCompletedDailyRoutinesForWeek(idRotinaSemanal: Int) {
        viewModelScope.launch {
            try {
                val res =
                    apiRotinaSemanal.showCompletedDailyRoutinesByRotinaSemanalId(idRotinaSemanal)
                val validationOfSucess =
                    res.isSuccessful && (res.body() != null || res.body()!! > 0)
                if (validationOfSucess) {
                    _rotinasDiariasConcluidasSemana.value = res.body()!!
                }
                Log.i("API", "Quantidade de rotinas diárias concluidas na semana: ${res.body()}")
            } catch (e: Exception) {
                Log.e("API","Erro na API para buscar a quantidade de rotinas diárias concluidas: ${e.message}")
            }
        }
    }
}