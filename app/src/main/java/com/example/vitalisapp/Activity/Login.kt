package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.Entity.Usuario.loginUsuario
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme
import loginService

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginCliente(
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
fun LoginCliente(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    var nickname by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    NavHost(modifier = modifier, navController = navController, startDestination = "login") {
        composable("login") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bem-vindo\nde volta",
                    fontFamily = MavenPro,
                    style = TextStyle(
                        fontSize = 54.sp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color(100, 194, 115), Color(115, 74, 145))
                        ),
                        textAlign = TextAlign.Center,
                        lineHeight = 56.sp
                    ),
                    modifier = Modifier
                        .padding(top = 54.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text(text = "Seu Apelido:", fontFamily = MavenPro) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text(text = "Senha:", fontFamily = MavenPro) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        val login = loginUsuario(0, nickname, senha)
                        loginService(login)

                        navController.navigate("home") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(72, 183, 90))
                ) {
                    Text(text = "Entrar", fontFamily = MavenPro)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("homePersonal") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(134, 86, 169))
                ) {
                    Text(text = "Entrar personal", fontFamily = MavenPro)
                }
                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = { navController.navigate("cadastro") }) {
                    Text(
                        text = "Ainda não tem conta? ",
                        fontFamily = MavenPro,
                        color = Color.White
                    )
                    Text(
                        text = " Clique aqui!",
                        fontFamily = MavenPro,
                        color = Color(43, 110, 44)
                    )
                }
            }
        }
        composable("home") { Home(name = name, navController) }
        composable("cadastro") { CadastroCliente(name = name, navController) }
        composable("perfil") { PerfilUsuario(name = name, navController) }
        composable("perfilPersonal") { PerfilPersonal(name = name, navController) }
        composable("homePersonal") { HomeProfessor(name = name, navController) }
        composable("exercicios") { GaleriaExercicio(name = name, navController) }
        composable("refeicao") { Refeicao(name = name, navController) }
// composable("relatorio") { RefeicaoTela() }
        composable("busca") { BuscaAcademia(name = name, navController) }
        composable("chat") { ConversaChat(name = name, navController) }
        composable("chatPersonal") { ChatPersonal(name = name, navController) }
        composable("galeria") { Galeria(name = name, navController) }
        composable("planos") { TelaPlano(name = name, navController) }
        }
    }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview7() {
    VitalisAppTheme {
        LoginCliente("Android", rememberNavController())
    }
}