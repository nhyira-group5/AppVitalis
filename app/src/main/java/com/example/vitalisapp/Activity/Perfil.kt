package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.RetrofitService
import com.example.vitalisapp.View.Usuario.Personal
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.example.vitalisapp.View.Usuario.UsuarioGet
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Perfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PerfilUsuario(
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
fun PerfilUsuario(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    val apiUsuario = RetrofitService.getApiUsuario()
    var usuario by remember { mutableStateOf<UsuarioGet?>(null) }
    var personal by remember { mutableStateOf<Personal?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = apiUsuario.getUsuarioById(1)
            if (response.isSuccessful) {
                usuario = response.body()

                usuario?.let {
                    if (it.tipo == TipoUsuario.PERSONAL) {
                        val personalResponse = apiUsuario.getPersonalById(it.id)
                        if (personalResponse.isSuccessful) {
                            personal = personalResponse.body()
                        } else {
                            errorMessage = "Erro ao carregar personal: ${personalResponse.code()}"
                        }
                    }
                }
            } else {
                errorMessage = "Erro: ${response.code()}"
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
        Menu(navController)

        if (usuario != null) {
            CartaoInfo(
                tipoUsuario = "usuario",
                imagemUsuario = R.mipmap.usuarioperfil,
                nome = usuario!!.nome,
                email = usuario!!.email,
                nickname = usuario!!.nickname,
                sexo = usuario!!.sexo,
                aniversario = usuario!!.dtNasc,
                onEditClick = {}
            )

            CardInfo(
                tipo = "usuario",
                fumante = usuario!!.fumante,
                alcoolatra = usuario!!.alcoolatra,
                deficiente = usuario!!.deficiente,
                problemaCardiaco = usuario!!.problemaCardiaco,
                peso = usuario!!.peso,
                altura = usuario!!.altura,
                meta = usuario!!.meta
            )

            if (personal != null) {
                CartaoAfiliacao(
                    afiliadoComPersonal = true,
                    nomePersonal = personal!!.nome,
                    emailPersonal = personal!!.email,
                    validadeAfiliacao = "12/2024 à 12/2025",
                    usernamePersonal = personal!!.nickname,
                    especialidade = personal!!.especialidades,
                    onClickVerPersonal = {}
                )
            }
        } else if (errorMessage != null) {
            Text(text = errorMessage!!, color = Color.Red)
        } else {
            Text(text = "Carregando...", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun GreetingPreview9() {
    VitalisAppTheme {
        PerfilUsuario("Android", rememberNavController())
    }
}