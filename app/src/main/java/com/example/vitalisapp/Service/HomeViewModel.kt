package com.example.vitalisapp.Service

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.Entity.Refeicao.dto.RefeicaoExibitionDto
import com.example.vitalisapp.Entity.RotinaDiaria.dto.RotinaDiariaExibitionDto
import com.example.vitalisapp.Entity.Treino.dto.TreinoExibitionDto
import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiTreino
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiRotinaDiaria: ApiRotinaDiaria
    private val apiRotinaSemanal: ApiRotinaSemanal
    private val apiRefeicao: ApiRefeicao
    private val apiTreino: ApiTreino

    init {
        apiRotinaDiaria = RetrofitService.getApiRotinaDiaria()
        apiRotinaSemanal = RetrofitService.getApiRotinaSemanal()
        apiRefeicao = RetrofitService.getApiRefeicao()
        apiTreino = RetrofitService.getApiTreino()
    }

    // Criar a busca da rotina do usuário
    // Criar a busca da rotina mensal atual
    // Criar a busca da rotina semanal atual
    // Treinos concluidos e totais no dia

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
            throw e
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