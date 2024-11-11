package com.example.vitalisapp.Activity

import ObjectPersonal
import ObjectPersonal.dtNasc
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.R
import com.example.vitalisapp.View.Usuario.TipoUsuario
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
    var sexo by remember { mutableStateOf("") }
    var masc by remember { mutableStateOf(false) }
    var fem by remember { mutableStateOf(false) }

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
                painter = painterResource(id = R.mipmap.logoroxo),
                contentDescription = "Imagem",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RectangleShape)
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.instrutor),
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
            text = stringResource(R.string.cadastro_instrutor),
            color = Color(168, 123, 199),
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
            onValueChange = { dataNascimento = it },
            label = "Data de nascimento",
        )
        InputText(
            value = senha,
            onValueChange = {senha = it},
            label = "Senha"
        )
        InputText(
            value = confirmarSenha,
            onValueChange = {confirmarSenha = it},
            label = "Confirmar senha"
        )
        InputText(
            value = cpf,
            onValueChange = {cpf = it},
            label = "CPF",
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
                isPersonal = true
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
                isPersonal = true
            )
        }


        Button(
            onClick = {

                ObjectPersonal.inicializar(nomeUsuario, apelido, cpf, formatarData(dataNascimento), senha, sexo, email,TipoUsuario.PERSONAL, null)
                ObjectPersonal.nome
                val cadastroPersonalDois = Intent(contexto, CadastroPersonalDois::class.java)
                cadastroPersonalDois.putExtra("nome",nomeUsuario)

                contexto.startActivity(cadastroPersonalDois)},
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(168, 123, 199)
            )
        ) {
            Text(
                text = "Próximo",
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