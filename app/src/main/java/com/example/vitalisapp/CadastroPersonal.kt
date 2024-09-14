package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class CadastroPersonal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroProfessor(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CadastroProfessor(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    var nomeUsuario by remember { mutableStateOf("") }
    var apelido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("M") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Black)
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Bem-vindo à nossa plataforma! Cadastre-se para acessar recursos e trabalhar conosco.",
                color = Color.Black,
                fontFamily = MavenPro,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
                    .fillMaxWidth(0.6f)
            )

            Image(
                painter = painterResource(id = R.mipmap.logoroxo),
                contentDescription = "Imagem",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RectangleShape)
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            )

            Text(
                text = "É um Instrutor?",
                color = Color.Black,
                fontFamily = MavenPro,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(start = 16.dp, top = 16.dp)
            )

            Button(
                onClick = { /* lógica */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(168, 123, 199)),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Sou um aluno",
                    fontFamily = MavenPro,
                    color = Color.White)

            }
        }

        Text(
            text = "Cadastro Instrutor",
            color = Color(168, 123, 199),
            fontSize = 35.sp,
            fontFamily = MavenPro,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Text(
            text = "Quer acessar nossa aplicação? Vamos realizar seu cadastro!",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Insira algumas informações sobre você para fazermos o cadastro de sua conta!",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Nome do Usuário",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Apelido",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Email",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Data de nascimento",
                fontFamily = MavenPro,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Senha",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Confirmar senha",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "CPF",
                color = Color.White) },
            placeholder = { Text(
                text = "123.456.789-10",
                fontFamily = MavenPro,
                color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "Sexo:",
                fontFamily = MavenPro,
                color = Color.White)
            RadioButton(
                selected = true,
                onClick = { /* lógica */ },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(168, 123, 199),
                    unselectedColor = Color.White)
            )
            Text(
                text = "M",
                fontFamily = MavenPro,
                color = Color.White)
            RadioButton(
                selected = false,
                onClick = { /* lógica */ },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(168, 123, 199),
                    unselectedColor = Color.White)
            )
            Text(
                text = "F",
                fontFamily = MavenPro,
                color = Color.White)
        }

        Button(
            onClick = {
                val cadastroUsuarioDois = Intent(contexto, CadastroUsuarioDois::class.java)
                contexto.startActivity(cadastroUsuarioDois)},
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(168, 123, 199)
            )
        ) {
            Text(
                text = "Criar conta",
                fontFamily = MavenPro,
                color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun GreetingPreview4() {
    VitalisAppTheme {
        CadastroProfessor("Android")
    }
}