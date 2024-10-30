package com.example.vitalisapp.Service
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vitalisapp.Entity.Usuario.loginRetornoUsuario
import com.example.vitalisapp.RetrofitService
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.example.vitalisapp.View.Usuario.Usuario
import com.example.vitalisapp.View.Usuario.loginUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
class CadastroUsuarioService : ViewModel(){


    private val _login = MutableStateFlow(loginRetornoUsuario())
    val login = _login.asStateFlow()

    val apiService = RetrofitService.getApiUsuario()
    var itemAtual: loginRetornoUsuario? = null

    fun registerUsuario(
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
//        val apiService = RetrofitService.getApiUsuario();
            try {
                val response = apiService.postUsuario(user)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        Log.i("api","User registered successfully!")
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("api","Registration failed: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("api","Error: ${e.message}")
                }
            }
        }
    }
    fun loginService(loginUsuario: loginUsuario){
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val response = apiService.loginUsuario(loginUsuario)
                    if (response.isSuccessful) {
                        _login.value = response.body()!!
                        Log.i("Sucesso", response.message())
                        Log.i("Sucesso", response.body().toString())
                        Log.i("Sucesso", _login.value.toString())
                    } else {
                        Log.e("apis", "Erro ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e("api", "Error: ${e.message}")
                    }
                }
            }.join()  // Garante que a corrotina termine antes de continuar
        }
    }



}