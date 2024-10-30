package com.example.vitalisapp.Service

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.DTO.Meta.MetaExibitionDto
import com.example.vitalisapp.Entity.Meta.ExibitionMeta
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.asStateFlow

class MetaViewModel: ViewModel() {

    fun  getLista():List<ExibitionMeta>{
        val metaApi = RetrofitService.getApiMeta()
        GlobalScope.launch {
            try {
                val resposta = metaApi.getMeta()
                if (resposta.isSuccessful){
                    Log.i("api", "items da API: ${resposta.body()}")
                    _listaMetas.value = resposta.body()!!
                }else{
                    Log.e("api", "Erro ao buscar items ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception){
                Log.e("api", "Erro ao buscar items", exception)
            }
        }
        return _listaMetas.value
    }

    private val _listaMetas = mutableStateOf(listOf<ExibitionMeta>())
    val listaMetas: MutableState<List<ExibitionMeta>> = _listaMetas


}