package com.example.vitalisapp.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.Entity.Usuario.loginRetornoUsuario
import com.example.vitalisapp.Interface.ApiEndereco
import com.example.vitalisapp.RetrofitService
import com.example.vitalisapp.View.Endereco.CreateEndereco
import com.example.vitalisapp.View.Endereco.ExibitionEndereco
import com.example.vitalisapp.View.Usuario.Personal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnderecoViewModel : ViewModel() {


    private val _endereco = MutableStateFlow(ExibitionEndereco())
    val endereco = _endereco.asStateFlow()

//    private val _viaCep = MutableStateFlow(ApiEndereco.EnderecoResponse())
//    val viaCep = _viaCep.asStateFlow()

    val apiService = RetrofitService.getApiEndereco()
    val apiUsuario = RetrofitService.getApiUsuario()


    fun createEndereco(endereco: CreateEndereco, usuario: Personal){
        val enderecoCreate = endereco
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.postEndereco(enderecoCreate)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _endereco.value = response.body()!!
                        usuario.academiaId = response.body()!!.id!!
                        apiUsuario.postPersonal(usuario)
                        Log.i("Sucesso", response.message())
                        Log.i("Sucesso", response.body().toString())
                        Log.i("Sucesso", _endereco.value.toString())
//                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("erroCadas","Registration failed: ${response.errorBody().toString()}")
                        Log.e("erroCadas","Registration failed: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("apiCadas","Error: ${e.message}")
                }
            }
        }
    }

//    fun buscarEnderecoPorCep(cep: String) {
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val response = apiService.getEndereco(cep)
//                    if (response.isSuccessful) {
//                        _viaCep.value = response.body()!!
//                        Log.i("Sucesso", response.message())
//                        Log.i("Sucesso", response.body().toString())
//                        Log.i("Sucesso", _endereco.value.toString())
//                    } else {
//                        Log.e("erroCadas","Registration failed: ${response.errorBody().toString()}")
//                        Log.e("erroCadas","Registration failed: ${response.errorBody()?.string()}")
//                    }
//                } catch (e: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Log.e("apiViaCep","Error: ${e.message}")
//                    }
//            }
//
//    }
}


