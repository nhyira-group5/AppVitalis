package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import com.example.vitalisapp.DTO.RotinaMensal.RotinaMensalExibitionDto
import com.example.vitalisapp.DTO.RotinaSemanal.RotinaSemanalExibitionDto
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioExibitionDto
import com.example.vitalisapp.ViewModel.HomeViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme
import org.koin.android.ext.android.inject

class Inicio : ComponentActivity() {
    private val viewModel by viewModels<HomeViewModel>() // Chama o ViewModel aqui

    private val rotinaUsuario: RotinaUsuarioExibitionDto by inject()
    private val rotinaMensal: RotinaMensalExibitionDto by inject()
    private val rotinaSemanal: RotinaSemanalExibitionDto by inject()
    private val rotinaDiaria: RotinaDiariaExibitionDto by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        rotinaUsuario, rotinaMensal, rotinaSemanal, rotinaDiaria,
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
    rotinaUsuario: RotinaUsuarioExibitionDto,
    rotinaMensal: RotinaMensalExibitionDto,
    rotinaSemanal: RotinaSemanalExibitionDto,
    rotinaDiaria: RotinaDiariaExibitionDto,

    viewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current      // Obtém a configuração atual da tela
    val screenHeight = configuration.screenHeightDp.dp  // Calcula a altura da tela em dp

    // Gerenciador de estado
    // Responsável por gerenciar as mudanças na tela, muda estados
    val homeUiState by viewModel.homeUiState.collectAsState()

    // Buscando todos os valores do UiState
    // Valores esses que podem ser alterados, mudados de estado

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
        // Atribuindo valores das rotinas core para os seus respectivos KOIN
        rotinaUsuario.idRotinaUsuario = homeUiState.rotinaUsuario?.idRotinaUsuario
        rotinaUsuario.usuario = homeUiState.rotinaUsuario?.usuario
        rotinaUsuario.meta = homeUiState.rotinaUsuario?.meta
        rotinaUsuario.rotinaAlternativa = homeUiState.rotinaUsuario?.rotinaAlternativa

        rotinaMensal.idRotinaMensal = homeUiState.rotinaMensal?.idRotinaMensal
        rotinaMensal.ano = homeUiState.rotinaMensal?.ano
        rotinaMensal.mes = homeUiState.rotinaMensal?.mes

        rotinaSemanal.idRotinaSemanal = homeUiState.rotinaSemanal?.idRotinaSemanal
        rotinaSemanal.numSemana = homeUiState.rotinaSemanal?.numSemana
        rotinaSemanal.concluido = homeUiState.rotinaSemanal?.concluido
        rotinaSemanal.rotinaMensal = homeUiState.rotinaSemanal?.rotinaMensal
        rotinaSemanal.rotinasDiarias = homeUiState.rotinaSemanal?.rotinasDiarias

        rotinaDiaria.idRotinaDiaria = homeUiState.rotinaDiaria?.idRotinaDiaria
        rotinaDiaria.dia = homeUiState.rotinaDiaria?.dia
        rotinaDiaria.concluido = homeUiState.rotinaDiaria?.concluido
        rotinaDiaria.rotinaSemanal = homeUiState.rotinaDiaria?.rotinaSemanal
        rotinaDiaria.refeicaoDiaria = homeUiState.rotinaDiaria?.refeicaoDiaria
        rotinaDiaria.totalExercicios = homeUiState.rotinaDiaria?.totalExercicios
        rotinaDiaria.totalExerciciosConcluidos = homeUiState.rotinaDiaria?.totalExerciciosConcluidos

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight)
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Menu(navController)
            Text(
                text = stringResource(R.string.Bem_vindo, nomeLogin),
                fontFamily = MavenPro,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
            )
            KpiHome(
                refeicoesTotaisDiaria, refeicoesConcluidasDiaria,
                treinosTotaisDiaria, treinosConcluidosDiaria,
                rotinasDiariasTotaisSemana, rotinasDiariasConcluidasSemana
            )
            DailyActivities(treinosDiarios, refeicoesDiarias)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Home() {
    VitalisAppTheme {
        Home(
            RotinaUsuarioExibitionDto(), RotinaMensalExibitionDto(), RotinaSemanalExibitionDto(), RotinaDiariaExibitionDto(),
            viewModel<HomeViewModel>(),
            rememberNavController(),
        )
    }
}