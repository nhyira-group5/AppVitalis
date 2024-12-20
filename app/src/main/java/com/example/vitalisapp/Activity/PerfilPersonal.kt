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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 251, 252))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuPersonal(navController)
        CartaoInfo(
            tipoUsuario = "personal",
            imagemUsuario = R.mipmap.usuarioperfil,
            nome = "Marcos da SIlva",
            email = "marcos@gmail.com",
            nickname = "marCOS23!",
            sexo = "Masculino",
            aniversario = "17/01/1998",
            especialidade = "emagrecimento",
            graduacao = "31/12/2022",
            onEditClick = {}
        )
//        CardInfo(
//            tipo = "personal",
//            cep = "08451-050",
//            logradouro = "Rua serra das araras",
//            numero = "123",
//            bairro = "Vila Yolanda (Lageado)",
//            cidade = "São Paulo",
//            estado = "SP"
//        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview10() {
    VitalisAppTheme {
        PerfilPersonal("Android", rememberNavController())
    }
}