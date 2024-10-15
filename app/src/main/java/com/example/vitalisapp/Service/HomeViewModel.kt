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

    fun getUserRoutineByUserId(idUsuario: Int): RotinaUsuarioExibitionDto? {
        viewModelScope.launch {
            try {
                val res = apiRotinaUsuario.showByUserId(idUsuario)
                if (res.isSuccessful) {
                    _rotinaUsuario.value = res.body()
                    Log.i("API", "Rotina de usuário encontrada: ${res.body()}")
                } else {
                    Log.e("API", "Erro ao buscar a rotina do usuário: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro na API ao buscar a rotina do usuário: ${e.message}")
                throw ApiException("Busca da rotina do usuário", e.message)
            }
        }
        return rotinaUsuario.value
    }

    fun getMonthRoutineByUserIdAndMonth(idUsuario: Int): RotinaMensalExibitionDto? {
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
        return rotinaMensal.value
    }

     fun getActualWeekRoutine(idUsuario: Int): RotinaSemanalExibitionDto? {
        val currentYear = LocalDate.now().year
        val currentMonth = LocalDate.now().monthValue
        val currentDay = LocalDate.now().dayOfMonth

        // Isso que dá não ter padronização no back rsrs (eu que fiz o back)
         viewModelScope.launch {
             try {
                 val res = apiRotinaSemanal.showByUserId(idUsuario)
                 if (res.isSuccessful) {
                     val userWeekRoutines = res.body()
                     Log.i( "console.log", "Lista de rotinas semanais para o user de ID $idUsuario encontrada: $userWeekRoutines")
                     if (userWeekRoutines.isNullOrEmpty()) {
                         Log.e("API", "Lista de rotinas semanais vazia para o user de ID $idUsuario")
                         throw Exception("Lista de rotinas semanais vazia para o user de ID $idUsuario")
                     }
                     val currentMonthWeeksRoutines = userWeekRoutines.filter { it.rotinaMensal.ano == currentYear && it.rotinaMensal.mes == currentMonth }
                     if (currentMonthWeeksRoutines.isEmpty()) {
                         Log.e("API", "Nenhuma rotina semanal para o mês atual para o usuário $idUsuario")
                         throw Exception("Nenhuma rotina semanal para o mês atual para o usuário $idUsuario\"")
                     }

                     val currentNumSemWeek = (currentDay - 1) / 7 + 1 // Calcula o número da semana (1 a 5)
                     _rotinaSemanal.value = currentMonthWeeksRoutines.find { it.numSemana == currentNumSemWeek }
                     if (_rotinaSemanal.value == null) {
                         Log.e("API", "Nenhuma rotina semanal encontrada para a semana $currentNumSemWeek")
                         throw Exception("Nenhuma rotina semanal encontrada para a semana $currentNumSemWeek")
                     } else {
                         Log.i("API","Rotina semanal encontrada: ${_rotinaSemanal.value}")
                     }
                 } else {
                     Log.e("API","Erro ao buscar as rotinas semanais para o usuário: ${res.errorBody()}")
                 }
             } catch (e: Exception) {
                 Log.e("API", "Erro na API ao buscar a rotina semanal atual: ${e.message}")
                 throw ApiException("Buscar rotina semanal atual", e.message)
             }
         }
         return rotinaSemanal.value
    }

    fun getDailyRoutine(idRotinaSemanal: Int): RotinaDiariaExibitionDto? {
        viewModelScope.launch {
            try {
                val res = apiRotinaDiaria.showByRotinaSemanalId(idRotinaSemanal)
                val dailyRoutinesForWeek = res.body()
                if (res.isSuccessful && !dailyRoutinesForWeek.isNullOrEmpty()) {
                    Log.i("console.log", "Busca de rotinas diárias para a rotina semanal ID $idRotinaSemanal : ${res.body()}")

                    val currentDayOfWeek = LocalDate.now().dayOfWeek.value % 7 + 1 // 1-7 (domingo a sábado)
                    Log.i("console.log", "Dia da semana atual: $currentDayOfWeek (1-7, Dom-Sab)")

                    _rotinaDiaria.value = dailyRoutinesForWeek.find { it.dia == currentDayOfWeek }
                    if (rotinaDiaria.value == null) {
                        Log.e("API", "Nenhuma rotina diária correspondente para o dia da semana atual")
                    } else {
                        Log.i("API", "Rotina diária encontrada: ${rotinaDiaria.value}")
                    }
                } else {
                    Log.e("API", "Erro ao buscar as rotinas diárias de uma rotina semanal: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro na API para buscar a rotina diária atual: ${e.message}")
                throw ApiException("Buscar rotina diária atual", e.message)
            }
        }
        return rotinaDiaria.value
    }

    fun getTrainingsFromDailyRoutine(idRotinaDiaria: Int): MutableList<TreinoExibitionDto> {
        viewModelScope.launch {
            try {
                val res = apiTreino.showByRotinaDiariaId(idRotinaDiaria)
                val validationOfSucess = res.isSuccessful && !res.body().isNullOrEmpty()
                if (validationOfSucess) {
                    _treinosDiarios.value.clear()
                    res.body()!!.forEach { _treinosDiarios.value.add(it) }
                }
                Log.i("API", "Quantidade de treinos buscados para a rotina diária: ${treinosDiarios.value.size}")
                Log.i("API","Treinos buscados para a rotina diária: ${treinosDiarios.value}")
            } catch (e: Exception) {
                Log.e("API","Erro na API para buscar os treinos da rotina diária: ${e.message}")
            }
        }
        return treinosDiarios.value
    }

    fun getMealsFromDailyRoutine(rotinaDiaria: RotinaDiariaExibitionDto): MutableList<RefeicaoExibitionDto> {
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
        return refeicoesDiarias.value
    }

    fun getQuantityCompletedTrainingsForDay(trainings: MutableList<TreinoExibitionDto>): Int {
        _treinosConcluidosDiaria.value = trainings.count { it.concluido == 1 }
        return treinosConcluidosDiaria.value
    }

    fun getQuantityTrainingsForDay(trainings: MutableList<TreinoExibitionDto>): Int {
        _treinosTotaisDiaria.value = trainings.count { it.concluido <= 1 }
        return treinosTotaisDiaria.value
    }

    // Feitas com a Rotina Diária pq as refeições estão salvas como DTOs de Refeição (Sem o campo "Concluido")
    fun getQuantityCompletedMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto?): Int {
        if (rotinaDiaria != null) _refeicoesConcluidasDiaria.value = rotinaDiaria.refeicaoDiaria.count { it.concluido == 1 }
        return refeicoesConcluidasDiaria.value
    }

    fun getQuantityMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto?): Int {
        if (rotinaDiaria != null) _refeicoesTotaisDiaria.value = rotinaDiaria.refeicaoDiaria.count { it.concluido <= 1 }
        return _refeicoesTotaisDiaria.value
    }

    fun getQuantityDailyRoutinesForWeek(idRotinaSemanal: Int): Int {
        viewModelScope.launch {
            try {
                val res = apiRotinaSemanal.showById(idRotinaSemanal)
                if (res.isSuccessful) repeat(res.body()!!.rotinaDiaria.size) { _rotinasDiariasTotaisSemana.value++ } else Log.e("API", "Erro para buscar a rotina semanal: ${res.errorBody()}")
            } catch (e: Exception) {
                Log.e("API","Erro na API para buscar a quantidade de rotinas diária na semana: ${e.message}")
            }
        }
        Log.i("API","Quantidade de rotinas diárias na semana de ID ${idRotinaSemanal}: ${rotinasDiariasTotaisSemana.value}")
        return rotinasDiariasTotaisSemana.value
    }

    fun getQuantityCompletedDailyRoutinesForWeek(idRotinaSemanal: Int): Int {
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
        return rotinasDiariasConcluidasSemana.value
    }
}