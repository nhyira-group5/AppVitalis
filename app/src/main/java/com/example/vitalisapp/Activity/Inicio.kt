package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitalisapp.Service.HomeViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<HomeViewModel>() // Chama o ViewModel aqui

            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        viewModel,
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Home(
    viewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Gerenciador de estado
        // Responsável por gerenciar as mudanças na tela, muda estados
    val homeUiState by viewModel.homeUiState.collectAsState()

    // Buscando todos os valores do UiState
        // Valores esses que podem ser alterados, mudados de estado
    val rotinaUsuario = homeUiState.rotinaUsuario
    val rotinaMensal = homeUiState.rotinaMensal
    val rotinaSemanal = homeUiState.rotinaSemanal
    val rotinaDiaria = homeUiState.rotinaDiaria
    val treinosDiarios = homeUiState.treinosDiarios
    val refeicoesDiarias = homeUiState.refeicoesDiarias
    val refeicoesConcluidasDiaria = homeUiState.refeicoesConcluidasDiaria
    val refeicoesTotaisDiaria = homeUiState.refeicoesTotaisDiaria
    val treinosConcluidosDiaria = homeUiState.treinosConcluidosDiaria
    val treinosTotaisDiaria = homeUiState.treinosTotaisDiaria
    val rotinasDiariasConcluidasSemana = homeUiState.rotinasDiariasConcluidasSemana
    val rotinasDiariasTotaisSemana = homeUiState.rotinasDiariasTotaisSemana
    val isLoading = homeUiState.isLoading

    val nomeLogin by remember { mutableStateOf("Poliana") }  // Pegar o valor buscado da tela de login e jogar aqui

    if (isLoading) {            // Não carregou? Então carrega um tela de loading
        LoadingScreen()         // Pode fazer uma tela de carregamento melhor kkkk
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Menu(navController)
            Text(
                text = stringResource(R.string.Bem_vindo, nomeLogin),
                fontFamily = MavenPro,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Atividade(
                treinosDiarios,
                refeicoesDiarias,
                refeicoesTotaisDiaria, refeicoesConcluidasDiaria,
                treinosTotaisDiaria, treinosConcluidosDiaria,
                rotinasDiariasTotaisSemana, rotinasDiariasConcluidasSemana
            )
        }
    }
}

// Teste tela de carregamento
@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Carregando...", fontSize = 24.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Home() {
    VitalisAppTheme {
        Home(
            viewModel<HomeViewModel>(),
            rememberNavController(),
        )
    }
}