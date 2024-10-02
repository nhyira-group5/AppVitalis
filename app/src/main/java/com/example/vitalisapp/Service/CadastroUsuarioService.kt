import android.util.Log
import com.example.vitalisapp.Entity.Usuario.TipoUsuario
import com.example.vitalisapp.Entity.Usuario.Usuario
import com.example.vitalisapp.Entity.Usuario.loginUsuario
import com.example.vitalisapp.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val apiService = RetrofitService.getApiUsuario();

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

fun loginService(
    loginUsuario: loginUsuario
){
  val login = loginUsuario;
    CoroutineScope(Dispatchers.IO).launch {
        val apiService = RetrofitService.getApiUsuario();
        try {

            val response = apiService.loginUsuario(login)
            if (response.isSuccessful) {

                withContext(Dispatchers.Main) {
                    Log.i("api","User registered successfully! ${response}")
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
