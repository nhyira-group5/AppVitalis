package com.example.vitalisapp.Activity

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.View.Usuario.TipoUsuario
import com.example.vitalisapp.R
import com.example.vitalisapp.Service.CadastroUsuarioService
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme



class CadastroUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroCliente(
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
fun CadastroCliente(name: String, navController: NavHostController, modifier: Modifier = Modifier, viewModel: CadastroUsuarioService = viewModel()) {
    var nomeUsuario by remember { mutableStateOf("") }
    var apelido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var masc by remember { mutableStateOf(false) }
    var fem by remember { mutableStateOf(false) }
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
                text = stringResource(R.string.cabecalho_intrutor),
                color = Color.Black,
                fontFamily = MavenPro,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
                    .fillMaxWidth(0.6f)
            )

            Image(
                painter = painterResource(id = R.mipmap.vitalislogo),
                contentDescription = "Imagem",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RectangleShape)
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            )

            Text(
                text = "É um Aluno?",
                color = Color.Black,
                fontFamily = MavenPro,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(start = 16.dp, top = 16.dp)
            )

            Button(
                onClick = {
                    val cadastroPersonal = Intent(contexto, CadastroPersonal::class.java)
                    contexto.startActivity(cadastroPersonal)},
                colors = ButtonDefaults.buttonColors(containerColor = Color(72, 183, 90)),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Sou um personal",
                    fontFamily = MavenPro,
                    color = Color.Black)

            }
        }

        Text(
            text = "Realizando cadastro",
            color = Color(72, 183, 90),
            fontSize = 35.sp,
            fontFamily = MavenPro,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 25.dp)
        )
        Text(
            text = stringResource(R.string.sub_cadastro1),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.sub_cadastro2),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = MavenPro,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        InputText(
            value = nomeUsuario,
            onValueChange = {nomeUsuario = it},
            label = "Nome do Usuário"
        )
        InputText(
            value = apelido,
            onValueChange = {apelido = it},
            label = "Apelido"
        )
        InputText(
            value = email,
            onValueChange = {email = it},
            label = "Email"
        )
        InputText(
            value = dataNascimento,
            onValueChange = {dataNascimento = it
                            },
            label = "Data de nascimento"
        )
        InputText(
            value = senha,
            onValueChange = {senha = it},
            label = "Senha"
        )
        InputText(
            value = confirmarSenha,
            onValueChange = {confirmarSenha = it},
            label = "Confirmar Senha"
        )
        InputText(
            value = cpf,
            onValueChange = {cpf = it},
            label = "CPF"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "Sexo:",
                fontFamily = MavenPro,
                color = Color.White
            )

            Checkbox(
                checked = masc,
                onCheckedChange = { checked ->
                    masc = checked
                    if (checked) {
                        fem = false
                        sexo = "M"
                    }
                },
                label = "M",
                isPersonal = false
            )

            Checkbox(
                checked = fem,
                onCheckedChange = { checked ->
                    fem = checked
                    if (checked) {
                        masc = false
                        sexo = "F"
                    }
                },
                label = "F",
                isPersonal = false // Defina se é personal ou usuário
            )
        }

        Button(
            onClick = {
                viewModel.registerUsuario(nomeUsuario, apelido, cpf, "2004-09-13", senha, sexo, email, TipoUsuario.USUARIO)
                val cadastroUsuarioDois = Intent(contexto, CadastroUsuarioDois::class.java)
                contexto.startActivity(cadastroUsuarioDois)},
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(72, 183, 90)
            )
        ) {
            Text(
                text = "Prosseguir",
                fontFamily = MavenPro,
                color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun GreetingPreview() {
    VitalisAppTheme {
        CadastroCliente("Android", rememberNavController())
    }
}