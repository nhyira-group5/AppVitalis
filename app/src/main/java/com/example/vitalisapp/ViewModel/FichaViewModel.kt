package com.example.vitalisapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.Entity.Ficha.ExibitionFicha
import com.example.vitalisapp.Entity.Ficha.FichaCriacao
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FichaViewModel : ViewModel() {

    private val _ficha = MutableStateFlow(ExibitionFicha())
    val ficha = _ficha.asStateFlow()

    private val _globalUiState = MutableStateFlow(GlobalUiState())
    val globalUiState = _globalUiState.asStateFlow()

    fun createFicha(fichaCriacao: FichaCriacao) {
        val Ficha = fichaCriacao
        GlobalScope.launch {
            val apiFicha = RetrofitService.getApiFicha();
            try {
                val createFicha = apiFicha.create(Ficha)
                if (createFicha.isSuccessful()) { // se a resposta foi bem sucedida (chamada OK e resposta convertida com sucesso)
                    Log.i("api", "Fez a chamada :)")
                    Log.i("api", "Resposta: ${createFicha.body()}")
                } else {
                    // entra aqui se a chamada foi feita com sucesso, mas a resposta não foi convertida com sucesso
                    Log.e("api", "Erro na resposta: ${createFicha.errorBody()!!.string()}")
                }
            } catch (e: Exception) {
                // entra aqui se a chamada não foi feita com sucesso (ex: sem internet, API fora do ar etc)
                Log.e("api", "Erro na chamada: ${e.message}")
            }
        }
    }

    val apiFicha = RetrofitService.getApiFicha();
    fun getFicha(id: Int?) {
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val resposta = apiFicha.getFicha(id)
                    if (resposta.isSuccessful) {
                        Log.i("Sucesso", "items da API: ${resposta.body()}")
                        _ficha.value = resposta.body()!!
                        Log.e("ErroApi", "items da API: ${_ficha.value.toString()}")
                    } else {
                        Log.e("ErroApi", "Erro ao buscar items ${resposta.errorBody()?.string()}")
                    }
                } catch (exception: Exception) {
                    Log.e("api", "Erro ao buscar items", exception)
                }
            }.join()  // Garante que a corrotina termine antes de continuar
        }


    }
}