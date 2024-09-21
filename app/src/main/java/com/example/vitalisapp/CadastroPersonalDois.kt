package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
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

class CadastroPersonalDois : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SegundoPersonal(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SegundoPersonal(name: String, modifier: Modifier = Modifier) {
    var cep by remember { mutableStateOf("") }
    var rua by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }
    var dataFormacao by remember { mutableStateOf("") }
    var especialidade by remember { mutableStateOf("") }
    var espExpanded by remember { mutableStateOf(false) }
    val espOptions = listOf("Musculação", "Yoga", "Pilates", "Crossfit")

    val contexto = LocalContext.current

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
                onClick = {val cadastroUsuario= Intent(contexto,
                    CadastroUsuario::class.java)
                    contexto.startActivity(cadastroUsuario)},
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
            text = "Cadastro - Endereço",
            color = Color(168, 123, 199),
            fontSize = 35.sp,
            fontFamily = MavenPro,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Text(
            text = "Muito bem! Você está quase lá! Só mais alguns passos...",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "\n" +
                    "Agora insira algumas informações sobre a localização da academia aonde você trabalha.",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 10.dp, top= 5.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "CEP",
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
                text = "Rua",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Estado",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Bairro",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Número",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Complemento",
                fontFamily = MavenPro,
                color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(
                text = "Data de formação",
                fontFamily = MavenPro,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { espExpanded = true }
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = especialidade,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Especialidade", fontFamily = MavenPro, color = Color.White) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        EspecialidadeDropdownMenu(
            metaOptions = espOptions,
            metaExpanded = espExpanded,
            onMetaChange = { selectedMeta ->
                especialidade = selectedMeta
                espExpanded = false
            },
            onDismissRequest = { espExpanded = false }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {val cadastroPersonal = Intent(contexto, CadastroPersonal::class.java)
                contexto.startActivity(cadastroPersonal)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                text = "Corrigir dados",
                fontFamily = MavenPro,
                color = Color.White)
        }
        Button(
            onClick = {
                val login = Intent(contexto, Login::class.java)
                contexto.startActivity(login)},
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

@Composable
fun EspecialidadeDropdownMenu(
    metaOptions: List<String>,
    metaExpanded: Boolean,
    onMetaChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = metaExpanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        metaOptions.forEach { option ->
            DropdownMenuItem(onClick = {
                onMetaChange(option)
            }) {
                Text(
                    text = option,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview5() {
    VitalisAppTheme {
        SegundoPersonal("Android")
    }
}