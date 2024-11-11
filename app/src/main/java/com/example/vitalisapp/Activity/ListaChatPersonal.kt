package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ListaChatPersonal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatPersonal(
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
fun ChatPersonal(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuPersonal(navController)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(168, 123, 199), RoundedCornerShape(24.dp))
                .padding(28.dp)
        ) {
            Column {
                Text(
                    text = "Alunos Afiliados",
                    color = Color.White,
                    fontFamily = MavenPro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                CardConversa(
                    imagem = R.mipmap.foto,
                    nome = "Danilo de Souza",
                    mensagem = "Lorem Ipsum is simply dummy text of the printing...",
                    hora = "00:00",
                    onClick = {
//                        val chatPersonal = Intent(contexto, ChatPersonal::class.java)
//                        contexto.startActivity(chatPersonal)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Conversas passadas",
                    color = Color.White,
                    fontFamily = MavenPro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                CardConversa(
                    imagem = R.mipmap.foto,
                    nome = "Marcelo da Silva",
                    mensagem = "Lorem Ipsum is simply dummy text of the printing...",
                    hora = "00:00",
                    backgroundColor = Color(216, 203, 226, 255),
                    onClick = {
//                        val chatPersonal = Intent(contexto, ChatPersonal::class.java)
//                    contexto.startActivity(chatPersonal)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview19() {
    VitalisAppTheme {
        ChatPersonal("Android", rememberNavController())
    }
}