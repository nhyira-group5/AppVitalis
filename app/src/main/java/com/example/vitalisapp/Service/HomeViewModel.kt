package com.example.vitalisapp.Service

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.Entity.Refeicao.dto.RefeicaoExibitionDto
import com.example.vitalisapp.Entity.RotinaDiaria.dto.RotinaDiariaExibitionDto
import com.example.vitalisapp.Entity.RotinaMensal.dto.RotinaMensalExibitionDto
import com.example.vitalisapp.Entity.RotinaSemanal.dto.RotinaSemanalExibitionDto
import com.example.vitalisapp.Entity.RotinaUsuario.dto.RotinaUsuarioExibitionDto
import com.example.vitalisapp.Entity.Treino.dto.TreinoExibitionDto
import com.example.vitalisapp.Exceptions.ApiException
import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.Interface.ApiTreino
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel : ViewModel() {
    private val apiRotinaDiaria: ApiRotinaDiaria
    private val apiRotinaSemanal: ApiRotinaSemanal
    private val apiRotinaMensal: ApiRotinaMensal
    private val apiRotinaUsuario: ApiRotinaUsuario
    private val apiRefeicao: ApiRefeicao
    private val apiTreino: ApiTreino

    init {
        apiRotinaDiaria = RetrofitService.getApiRotinaDiaria()
        apiRotinaSemanal = RetrofitService.getApiRotinaSemanal()
        apiRotinaMensal = RetrofitService.getApiRotinaMensal()
        apiRotinaUsuario = RetrofitService.getApiRotinaUsuario()
        apiRefeicao = RetrofitService.getApiRefeicao()
        apiTreino = RetrofitService.getApiTreino()
    }

    suspend fun getUserRoutineByUserId(idUsuario: Int): RotinaUsuarioExibitionDto {
        return try {
            val res = apiRotinaUsuario.showByUserId(idUsuario)
            if (res.isSuccessful) {
                Log.i("API", "Rotina de usuário encontrada: ${res.body()}")
                res.body() ?: throw Exception("Resposta vazia da API")
            } else {
                Log.e("API", "Erro ao buscar a rotina do usuário: ${res.errorBody()}")
                throw Exception("Erro ao buscar a rotina do usuário: ${res.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("API", "Erro na API ao buscar a rotina do usuário: ${e.message}")
            throw ApiException("Busca da rotina do usuário", e.message)
        }
    }

    suspend fun getMonthRoutineByUserIdAndMonth(
        idUsuario: Int,
        month: Int
    ): RotinaMensalExibitionDto {
        return try {
            val res = apiRotinaMensal.showByUserIdAndMonth(idUsuario, month)
            if (res.isSuccessful) {
                Log.i("API", "Rotina de mensal encontrada: ${res.body()}")
                res.body() ?: throw Exception("Resposta vazia da API")
            } else {
                Log.e("API", "Erro ao buscar a rotina mensal: ${res.errorBody()}")
                throw Exception("Erro ao buscar a rotina menal: ${res.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("API", "Erro na API ao buscar a rotina mensal: ${e.message}")
            throw ApiException("Buscar rotina mensal", e.message)
        }
    }

    suspend fun getActualWeekRoutine(idUsuario: Int): RotinaSemanalExibitionDto {
        val currentYear = LocalDate.now().year
        val currentMonth = LocalDate.now().monthValue
        val currentDay = LocalDate.now().dayOfMonth

        // Isso que dá não ter padronização no back rsrs (eu que fiz o back)
        return try {
            val res = apiRotinaSemanal.showByUserId(idUsuario)
            if (res.isSuccessful) {
                val userWeekRoutines = res.body()
                Log.i(
                    "console.log",
                    "Lista de rotinas semanais para o user de ID $idUsuario encontrada: $userWeekRoutines"
                )
                if (userWeekRoutines.isNullOrEmpty()) { throw Exception("Lista de rotinas semanais vazia para o user de ID $idUsuario") }
                val currentMonthWeeksRoutines = userWeekRoutines.filter { it.rotinaMensal.ano == currentYear && it.rotinaMensal.mes == currentMonth }
                if (currentMonthWeeksRoutines.isEmpty()) throw Exception("Nenhuma rotina semanal para o mês atual para o usuário")

                val currentNumSemWeek = (currentDay - 1) / 7 + 1 // Calcula o número da semana (1 a 5)
                val currentWeekRoutine = currentMonthWeeksRoutines.find { it.numSemana == currentNumSemWeek } ?: throw Exception("Nenhuma rotina semanal encontrada para a semana $currentNumSemWeek")
                currentWeekRoutine
            } else {
                Log.e(
                    "API",
                    "Erro ao buscar as rotinas semanais para o usuário: ${res.errorBody()}"
                )
                throw Exception("Erro ao buscar rotinas semanais pelo idUsuario: ${res.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("API", "Erro na API ao buscar a rotina semanal atual: ${e.message}")
            throw ApiException("Buscar rotina semanal atual", e.message)
        }
    }

    suspend fun getDailyRoutineOfTheDay(idRotinaDiaria: Int): RotinaDiariaExibitionDto {
        return try {
            val res = apiRotinaDiaria.showById(idRotinaDiaria)
            if (res.isSuccessful) {
                res.body().also {
                    Log.i("API", "Rotina diária atual buscada: $it")
                } ?: throw Exception("Resposta vazia da API")
            } else {
                throw Exception("Erro ao buscar a rotina diária: ${res.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("API", "Erro na API para buscar a rotina diária atual: ${e.message}")
            throw ApiException("Buscar rotina diária atual", e.message)
        }
    }

    fun getTrainingsFromDailyRoutine(idRotinaDiaria: Int): MutableList<TreinoExibitionDto> {
        var dailyTrainings: MutableList<TreinoExibitionDto> = ArrayList(0)
        GlobalScope.launch {
            try {
                val res = apiTreino.showByRotinaDiariaId(idRotinaDiaria)
                val validationOfSucess = res.isSuccessful && !res.body().isNullOrEmpty()
                if (validationOfSucess) {
                    dailyTrainings.clear()
                    res.body()!!.forEach { dailyTrainings.add(it) }
                }
                Log.i(
                    "API",
                    "Quantidade de treinos buscados para a rotina diária: ${dailyTrainings.size}"
                )
                Log.i(
                    "API",
                    "Treinos buscados para a rotina diária: ${dailyTrainings}"
                )
            } catch (e: Exception) {
                Log.e(
                    "API",
                    "Erro na API para buscar os treinos da rotina diária: ${e.message}"
                )
            }
        }
        return dailyTrainings
    }

    fun getMealsFromDailyRoutine(rotinaDiaria: RotinaDiariaExibitionDto): MutableList<RefeicaoExibitionDto> {
        var dailyMeals: MutableList<RefeicaoExibitionDto> = ArrayList(0)

        if (rotinaDiaria.refeicaoDiaria.isNotEmpty()) {
            rotinaDiaria.refeicaoDiaria.forEach {       // Mapper é o carai kkkkkkkkkkkkk fodasse
                GlobalScope.launch {
                    try {
                        val res = apiRefeicao.showById(it.idRotinaDiaria)
                        if (res.isSuccessful) dailyMeals.add(res.body()!!) else Log.e(
                            "API",
                            "Erro para buscar a refeição de ID ${it.idRotinaDiaria} atribuida a rotina diária: ${res.errorBody()}"
                        )
                    } catch (e: Exception) {
                        Log.e(
                            "API",
                            "Erro na API para buscar a refeição de ID ${it.idRotinaDiaria} atribuida a rotina diária: ${e.message}"
                        )
                    }
                }
            }
            Log.i(
                "API",
                "Quantidade de refeições atribuídas a rotina diária: ${dailyMeals.size}"
            )
            Log.i("API", "Refeições atribuídas a rotina diária: ${dailyMeals}")
        }
        return dailyMeals
    }

    fun getCompletedTrainingsForDay(trainings: MutableList<TreinoExibitionDto>): Int {
        return trainings.count { it.concluido == 1 }
    }

    fun getQuantityTrainingsForDay(trainings: MutableList<TreinoExibitionDto>): Int {
        return trainings.count { it.concluido <= 1 }
    }

    // Feitas com a Rotina Diária pq as refeições estão salvas como DTOs de Refeição (Sem o campo "Concluido")
    fun getCompletedMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto): Int {
        return rotinaDiaria.refeicaoDiaria.count { it.concluido == 1 }
    }

    fun getQuantityMealsForDay(rotinaDiaria: RotinaDiariaExibitionDto): Int {
        return rotinaDiaria.refeicaoDiaria.count { it.concluido == 1 }
    }

    fun getQuantityDailyRoutinesForWeek(idRotinaSemanal: Int): Int {
        var quantityOfDailyRoutines: Int = 0
        GlobalScope.launch {
            try {
                val res = apiRotinaSemanal.showById(idRotinaSemanal)
                if (res.isSuccessful) res.body()!!.rotinaDiaria.forEach { quantityOfDailyRoutines++ } else Log.e(
                    "API",
                    "Erro para buscar a rotina semanal: ${res.errorBody()}"
                )
            } catch (e: Exception) {
                Log.e(
                    "API",
                    "Erro na API para buscar a quantidade de rotinas diária na semana: ${e.message}"
                )
            }
        }
        Log.i(
            "API",
            "Quantidade de rotinas diárias na semana de ID ${idRotinaSemanal}: ${quantityOfDailyRoutines}"
        )
        return quantityOfDailyRoutines
    }

    fun getQuantityCompletedDailyRoutinesForWeek(idRotinaSemanal: Int): Int {
        var completedDailyRoutines: Int = 0
        GlobalScope.launch {
            try {
                val res =
                    apiRotinaSemanal.showCompletedDailyRoutinesByRotinaSemanalId(idRotinaSemanal)
                val validationOfSucess =
                    res.isSuccessful && (res.body() != null || res.body()!! > 0)
                if (validationOfSucess) {
                    completedDailyRoutines = res.body()!!
                }
                Log.i("API", "Quantidade de rotinas diárias concluidas na semana: ${res.body()}")
            } catch (e: Exception) {
                Log.e(
                    "API",
                    "Erro na API para buscar a quantidade de rotinas diárias concluidas: ${e.message}"
                )
            }
        }
        return completedDailyRoutines
    }

//    fun getRefeicaoPorSemana(idSemana: Int): MutableList<RefeicaoExibitionDto> {
//    var refeicoesSemanais: MutableList<RefeicaoExibitionDto> = ArrayList(0)
//        GlobalScope.launch {
//            try {
//                val res = apiRefeicao.showByRotinaSemanalId(idSemana)
//                if(res.isSuccessful) {
//                    if (!res.body().isNullOrEmpty()) refeicoesSemanais = res.body()!!
//                    Log.i("API", "Itens buscados para refeições na semana: ${res.body()}")
//                } else {
//                    Log.e("API", "Houve um erro ao buscar os itens referente a refeições semanais: ${res.errorBody()}")
//                }
//            } catch (e: Exception) {
//                Log.e("API", "Erro na API para buscar as refeições semanais: ${e.message}")
//            }
//        }
//
//        return refeicoesSemanais
//    }

//    fun getTreinosPorSemana(idSemana: Int): MutableList<TreinoExibitionAltDto> {
//    var treinosSemanais: MutableList<TreinoExibitionAltDto> = ArrayList(0)
//        GlobalScope.launch {
//            try {
//                val res = apiTreino.showByRotinaSemanalId(idSemana)
//                if(res.isSuccessful) {
//                    if (!res.body().isNullOrEmpty()) treinosSemanais = res.body()!!
//                    Log.i("API", "Itens buscados para treinos na semana: ${res.body()}")
//                } else {
//                    Log.e("API", "Houve um erro ao buscar os itens referente a treinos semanais: ${res.errorBody()}")
//                }
//            } catch (e: Exception) {
//                Log.e("API", "Erro na API para buscar os treinos semanais: ${e.message}")
//            }
//        }
//
//        return treinosSemanais
//    }
}