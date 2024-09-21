package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ConfirmacaoCadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Confirmacao(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Confirmacao(name: String, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val contexto = LocalContext.current
    val nomeCompleto = "João da Silva"
    val cpf = "123.456.789-00"
    val dataNascimento = "01/01/1990"
    val email = "joao.silva@example.com"
    val peso = "75 kg"
    val altura = "1.80 m"
    val sexo = "Masculino"

    val questions = listOf(
        "Algum médico já disse que você possui algum problema de coração e que só deveria realizar atividade física supervisionado por profissionais de saúde?" to true,
        "Você sente dores no peito quando pratica atividade física?" to false,
        "No último mês, você sentiu dores no peito quando praticou atividade física?" to true,
        "Você possui algum problema ósseo ou articular  que poderia ser piorado pela atividade física?" to false,
        "Você toma atualmente algum medicamento para pressão arterial?" to false,
        "Sabe de alguma outra razão pela qual você não deve praticar atividade física?" to true
    )
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Confirme suas informações",
                fontFamily = MavenPro,
                color = Color(72, 183, 90),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                fontSize = 25.sp
            )
            Text(
                text = "Antes de você acessar nosso sistema, você deve confirmar algumas informações sobre você e sua saúde.",
                color = Color.White,
                fontFamily = MavenPro,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Black)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Column {
                        Text(
                            text = "Confirme seus dados",
                            fontFamily = MavenPro,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nomeCompleto,
                            onValueChange = {},
                            label = { Text(text = "Nome completo",
                                fontFamily = MavenPro,) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = cpf,
                            onValueChange = {},
                            label = { Text(text = "CPF",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = dataNascimento,
                            onValueChange = {},
                            label = { Text(text = "Data de nascimento",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = {},
                            label = { Text(text = "E-mail",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = sexo,
                            onValueChange = {},
                            label = { Text(text = "Sexo",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = sexo,
                            onValueChange = {},
                            label = { Text(text = "Meta",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = peso,
                            onValueChange = {},
                            label = { Text(text = "Peso",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = altura,
                            onValueChange = {},
                            label = { Text(text = "Altura",
                                fontFamily = MavenPro) },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        questions.forEach { (question, isChecked) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = question, color = Color.Black, modifier = Modifier.weight(1f))
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = {},
                                    colors = CheckboxDefaults.colors(checkedColor = Color.Black)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    val cadastroUsuarioDois = Intent(contexto, CadastroUsuarioDois::class.java)
                                    contexto.startActivity(cadastroUsuarioDois) },
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
                                    val parq = Intent(contexto, ConfirmacaoParq::class.java)
                                    contexto.startActivity(parq)},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(72, 183, 90)
                                ),
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                Text(
                                    text = "Concluir cadastro",
                                    fontFamily = MavenPro)
                            }
                        }
                    }
                }
            }
        }
//            ConfirmacaoParq(
//                showDialog = showDialog,
//                onDismiss = { showDialog = false },
//                onAcceptRisks = {
//                    // Lógica quando aceitar os riscos
//                },
//                onTakeItEasy = {
//                    // Lógica quando pegar mais leve
//                }
//            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    VitalisAppTheme {
        Confirmacao("Android")
    }
}