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
            VitalisAppTheme {
                val viewModel by viewModels<HomeViewModel>()   // Chamando o viewModel

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
    var isLoading = remember { mutableStateOf(true) }

    // Pegando o atribuito EXPOSTO da View
    val rotinaUsuario by viewModel.rotinaUsuario.collectAsState()
    val rotinaMensal by viewModel.rotinaMensal.collectAsState()
    val rotinaSemanal by viewModel.rotinaSemanal.collectAsState()
    val rotinaDiaria by viewModel.rotinaDiaria.collectAsState()
    val treinosDiarios by viewModel.treinosDiarios.collectAsState()
    val refeicoesDiarias by viewModel.refeicoesDiarias.collectAsState()
    val refeicoesConcluidasDiaria by viewModel.refeicoesConcluidasDiaria.collectAsState()
    val refeicoesTotaisDiaria by viewModel.refeicoesTotaisDiaria.collectAsState()
    val treinosConcluidosDiaria by viewModel.treinosConcluidosDiaria.collectAsState()
    val treinosTotaisDiaria by viewModel.treinosTotaisDiaria.collectAsState()
    val rotinasDiariasConcluidasSemana by viewModel.rotinasDiariasConcluidasSemana.collectAsState()
    val rotinasDiariasTotaisSemana by viewModel.rotinasDiariasTotaisSemana.collectAsState()

    val nomeLogin = "Poliana" // Pegar o valor buscado da tela de login e jogar aqui

    /// Quando carrega a tela, faz essas func
    LaunchedEffect(Unit) {
        viewModel.getUserRoutineByUserId(1)                 // Passar ID buscado no login
        viewModel.getMonthRoutineByUserIdAndMonth(1)        // Passar ID buscado no login
        viewModel.getWeekRoutine(1)                          // Passar ID buscado no login
        viewModel.getDailyRoutine(rotinaSemanal!!.idRotinaSemanal)
        viewModel.getTrainingsFromDailyRoutine(rotinaDiaria!!.idRotinaDiaria)
//        viewModel.getMealsFromDailyRoutine(rotinaDiaria!!)
//        viewModel.getQuantityCompletedMealsForDay(rotinaDiaria)
//        viewModel.getQuantityMealsForDay(rotinaDiaria)
//        viewModel.getQuantityCompletedTrainingsForDay(treinosDiarios)
//        viewModel.getQuantityTrainingsForDay(treinosDiarios)
//        viewModel.getQuantityCompletedDailyRoutinesForWeek(rotinaSemanal!!.idRotinaSemanal)
//        viewModel.getQuantityCompletedDailyRoutinesForWeek(rotinaSemanal!!.idRotinaSemanal)

        isLoading.value = false  // Diz que carregou td
    }

    if (isLoading.value) {      // Carregou? Então carrega o conteudo
        LoadingScreen()         // Pode fazer uma tela de carregamento
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

//            if (rotinaUsuario != null) Text(text = "rotina usuario foi")
//            if (rotinaMensal != null) Text(text = "rotina mensal foi")
//            if (rotinaSemanal != null) Text(text = "rotina semanal foi: $rotinaSemanal")
            if (treinosDiarios.isNotEmpty()) Text(text = "treinos foi: $treinosDiarios")
            //if (refeicoesDiarias.isNotEmpty()) Text(text = "treinos foi: $refeicoesDiarias")
//
//        Button(onClick = { viewModel.getDailyRoutine(rotinaSemanal!!.idRotinaSemanal) }) {
//            Text(text = "gerar rotina diaria")
//        }
//        Text(
//            text = "$rotinaDiaria"
//        )

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
        Home(viewModel(), rememberNavController())
    }
}