package com.example.vitalisapp.Activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.vitalisapp.Entity.RotinaDiaria.dto.RotinaDiariaExibitionDto
import com.example.vitalisapp.Service.HomeViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
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
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = viewModel()

    val rotinaUsuario = viewModel.getUserRoutineByUserId(1)             // Passar ID buscado no login
    val rotinaMensal = viewModel.getMonthRoutineByUserIdAndMonth(1)     // Passar ID buscado no login
    val rotinaSemanal = viewModel.getActualWeekRoutine(1)               // Passar ID buscado no login
    val rotinaDiaria = rotinaSemanal?.let { viewModel.getDailyRoutine(it.idRotinaSemanal) }

    val treinosDiarios = rotinaDiaria?.let { viewModel.getTrainingsFromDailyRoutine(it.idRotinaDiaria) } ?: run {
            Log.e("Home", "Rotina diária é nula - Treinos Nulos")
            mutableListOf()
        }
    val refeicoesDiarias = rotinaDiaria?.let { viewModel.getMealsFromDailyRoutine(it) } ?: run {
        Log.e("Home", "Rotina diária é nula - Refeições Nulas")
        mutableListOf()
    }

    val refeicoesConcluidasDiaria = rotinaDiaria?.let { viewModel.getQuantityCompletedMealsForDay(it) } ?: 0
    val refeicoesTotaisDiaria = rotinaDiaria?.let { viewModel.getQuantityMealsForDay(it) } ?: 0
    val treinosConcluidosDiaria = treinosDiarios?.let { viewModel.getQuantityCompletedTrainingsForDay(it) } ?: 0
    val treinosTotaisDiaria = treinosDiarios?.let { viewModel.getQuantityTrainingsForDay(it) } ?: 0
    val rotinasDiariasConcluidasSemana = rotinaSemanal?.let { viewModel.getQuantityCompletedDailyRoutinesForWeek(it.idRotinaSemanal) } ?: 0
    val rotinasDiariasTotaisSemana = rotinaSemanal?.let { viewModel.getQuantityCompletedDailyRoutinesForWeek(it.idRotinaSemanal) } ?: 0

    val nomeLogin = "Poliana" // Pegar o valor buscado da tela de login e jogar aqui

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Home() {
    VitalisAppTheme {
        Home(rememberNavController())
    }
}