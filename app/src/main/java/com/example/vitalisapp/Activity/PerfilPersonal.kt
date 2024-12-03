package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.DTO.Usuario.PersonalExibitionDto
import com.example.vitalisapp.R
import com.example.vitalisapp.RetrofitService
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.View.Usuario.Personal
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class PerfilUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PerfilPersonal(
                        name = "Android",
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PerfilPersonal(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    val apiUsuario = RetrofitService.getApiUsuario()
    var personal by remember { mutableStateOf<PersonalExibitionDto?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = apiUsuario.getPersonalById(SessionLogin.id!!)
            if (response.isSuccessful) {
                personal = response.body()
            } else {
                val errorBodyString = response.errorBody()?.string() ?: "Erro desconhecido"
                android.util.Log.e("PerfilPersonal", "Erro ao carregar dados do personal: $errorBodyString")
                errorMessage = "Erro ao carregar dados do personal: $errorBodyString"
            }

        } catch (e: Exception) {
            errorMessage = "Falha ao carregar dados: ${e.message}"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 251, 252))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuPersonal(navController)

        if (personal != null) {
            CartaoInfo(
                tipoUsuario = "personal",
                imagemUrl = personal?.midia?.caminho ?: "",
                nome = personal!!.nome ?: "N/A",
                email = personal!!.email ?: "N/A",
                nickname = personal!!.nickname ?: "N/A",
                sexo = personal!!.sexo ?: "N/A",
                aniversario = personal!!.dtNasc ?: "N/A",
                onEditClick = {}
            )
        } else if (errorMessage != null) {
            Text(text = errorMessage!!, color = Color.Red)
        } else {
            Text(text = "Carregando...", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview10() {
    VitalisAppTheme {
        PerfilPersonal("Android", rememberNavController())
    }
}