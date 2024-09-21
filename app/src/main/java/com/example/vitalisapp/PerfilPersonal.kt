package com.example.vitalisapp

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
        CartaoInfoPersonal()
        CartaoInfoEndereco()
    }
}

@Composable
fun CartaoInfoPersonal() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Informações Pessoais",
                fontFamily = MavenPro,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(134, 86, 169),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.usuarioperfil),
                    contentDescription = "Foto Usuário",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Image(
                    painter = painterResource(id = R.mipmap.botaopersonal),
                    contentDescription = "Botão",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .clickable { onEditClick() }
                        .align(Alignment.BottomEnd)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                ItemInfoPersonal("Nome completo:", "Marcos da Silva")
                ItemInfoPersonal("E-mail principal:", "FulanoSailva@gmail.com")
                ItemInfoPersonal("Nickname:", "marC0S23!")
                ItemInfoPersonal("Data de nascimento:", "17 / 01 / 1995")
                ItemInfoPersonal("Sexo:", "Masculino")
                ItemInfoPersonal("Especialidade:", "Emagrecimento")
                ItemInfoPersonal("Data de formação:", "31/12/2022")
            }
        }
    }
}

@Composable
fun CartaoInfoEndereco() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Informações de Endereço",
                fontFamily = MavenPro,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(134, 86, 169),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                ItemInfoPersonal("CEP:", "08451-050")
                ItemInfoPersonal("Logradouro:", "Rua serra das araras")
                ItemInfoPersonal("Número:", "123")
                ItemInfoPersonal("Bairro:", "Vila Yolanda (Lageado)")
                ItemInfoPersonal("Cidade:", "São Paulo")
                ItemInfoPersonal("Estado", "SP")
            }
        }
    }
}

@Composable
fun ItemInfoPersonal(label: String, valor: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontFamily = MavenPro,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = valor,
            fontFamily = MavenPro,
            fontSize = 16.sp,
            color = Color(24, 24, 27),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview10() {
    VitalisAppTheme {
        PerfilPersonal("Android", rememberNavController())
    }
}