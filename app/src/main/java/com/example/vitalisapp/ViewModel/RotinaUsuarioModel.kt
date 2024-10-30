package com.example.vitalisapp.Service

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioCreateEditDto
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioExibitionDto
import com.example.vitalisapp.Entity.Ficha.FichaCriacao
import com.example.vitalisapp.GlobalUiState
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RotinaUsuarioModel : ViewModel() {

    private val _rotinaUsuario = MutableStateFlow(RotinaUsuarioExibitionDto())
    val rotinaUsuario = _rotinaUsuario.asStateFlow()


    fun createRotinaUsuario(rotinaUsuarioCreateEditDto: RotinaUsuarioCreateEditDto){
        val rotUsuario = rotinaUsuarioCreateEditDto
        GlobalScope.launch {
            val apiRotina = RetrofitService.getApiRotinaUsuario();
            try {
                val createRotina = apiRotina.create(rotUsuario)
                if (createRotina.isSuccessful){
                    Log.i("api", "Fez a chamada :)")
                    Log.i("api", "Resposta: ${createRotina.body()}")
                }else {
                    Log.e("api", "Erro na resposta: ${createRotina.errorBody()!!.string()}")
                }
            }catch (e: Exception) {
                // entra aqui se a chamada não foi feita com sucesso (ex: sem internet, API fora do ar etc)
                Log.e("api", "Erro na chamada: ${e.message}")
            }
        }
    }




}