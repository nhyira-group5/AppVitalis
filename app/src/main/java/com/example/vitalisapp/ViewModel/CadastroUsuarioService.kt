package com.example.vitalisapp.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalisapp.Entity.Usuario.loginRetornoUsuario
import com.example.vitalisapp.RetrofitService
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.View.Usuario.Personal
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.example.vitalisapp.View.Usuario.Usuario
import com.example.vitalisapp.View.Usuario.UsuarioGet
import com.example.vitalisapp.View.Usuario.loginUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CadastroUsuarioService : ViewModel() {
    private val _login = MutableStateFlow(loginRetornoUsuario())
    val login = _login.asStateFlow()

    private val _Cadastro = MutableStateFlow(UsuarioGet())
    val Cadastro = _Cadastro.asStateFlow()

    val apiService = RetrofitService.getApiUsuario()
    var itemAtual: loginRetornoUsuario? = null

    private val _registroStatus = MutableStateFlow<Boolean?>(false)
    val registroStatus = _registroStatus.asStateFlow()

    fun registerUsuario(
        context: Context,
        nome: String,
        nickname: String,
        cpf: String,
        dtNasc: String, // Use LocalDate se precisar
        senha: String,
        sexo: String,
        email: String,
        tipo: TipoUsuario
    ) {
        val user = Usuario(
            nome = nome,
            nickname = nickname,
            cpf = cpf,
            dtNasc = dtNasc,
            senha = senha,
            sexo = sexo,
            email = email,
            tipo = tipo
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.postUsuario(user)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _Cadastro.value = response.body()!!
                        _registroStatus.value = true
                        Log.i("Sucesso", response.message())
                        Log.i("Sucesso", response.body().toString())
                        Log.i("Sucesso", _Cadastro.value.toString())
                        Toast.makeText(
                            context,
                            "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e(
                            "erroCadas",
                            "Registration failed: ${response.errorBody().toString()}"
                        )
                        Log.e("erroCadas", "Registration failed: ${response.errorBody()?.string()}")
                        Toast.makeText(
                            context,
                            "Dados invalidos, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("apiCadas", "Error: ${e.message}")
                }
            }
        }
    }

    fun registerPersonal(
        context: Context,
        personal: Personal
    ) {
        val user = personal
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.postPersonal(user)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        Log.i("sucessoCadas", "User registered successfully!")
                        Toast.makeText(
                            context,
                            "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e(
                            "erroCadas",
                            "Registration failed: ${response.errorBody().toString()}"
                        )
                        Log.e("erroCadas", "Registration failed: ${response.errorBody()?.string()}")
                        Toast.makeText(
                            context,
                            "Dados invalidos, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("apiCadas", "Error: ${e.message}")
                }
            }
        }
    }

    fun loginService(loginUsuario: loginUsuario, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.loginUsuario(loginUsuario)
                _login.value = loginRetornoUsuario()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        withContext(Dispatchers.Main) {
                            _login.value = body
                            Log.i("Sucesso", response.message())
                            Log.i("Sucesso", body.toString())
                        }
                    }
                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("apis", "Erro ${response.errorBody()?.string()}")
                        Toast.makeText(
                            context,
                            "nickname ou senha invalidos! Tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("api", "Error: ${e.message}")
                }
            }
        }
    }

    fun getUsuarioAtivo(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resposta = apiService.getUsuarioPagamentoAtivo(id!!)
                if (resposta.isSuccessful) {
                    Log.i("Sucesso", "items da API: ${resposta.body()}")
                    SessionLogin.meta = resposta.body()!!.meta!!.idMeta
                    SessionLogin.pagamentoAtivo = resposta.body()!!.pagamentoAtivo
                    Log.i("Sucesso", "items da API: ${SessionLogin.toString()}")
                } else {
                    Log.e("ErroApi", "Erro ao buscar items ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar items", exception)
            }
        }
    }
}

//
//    fun loginService(loginUsuario: loginUsuario){
//        runBlocking {
//            launch(Dispatchers.IO) {
//                try {
//                    val response = apiService.loginUsuario(loginUsuario)
//                    if (response.isSuccessful) {
//                        _login.value = response.body()!!
//                        Log.i("Sucesso", response.message())
//                        Log.i("Sucesso", response.body().toString())
//                        Log.i("Sucesso", _login.value.toString())
////                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Log.e("apis", "Erro ${response.errorBody()?.string()}")
////                        Toast.makeText(context, "nickname ou senha invalidos! Tente novamente!", Toast.LENGTH_SHORT).show()
//                    }
//                } catch (e: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Log.e("api", "Error: ${e.message}")
//                    }
//                }
//            }.join()
//        }
//    }