package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.vitalisapp.ViewModel.ListaRefeicaoViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ListaRefeicao : ComponentActivity() {
    private val viewModel by viewModels<ListaRefeicaoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Refeicoes(
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
fun Refeicoes(
    vielModel: ListaRefeicaoViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    //val contexto = LocalContext.current
    val listaRefeicaoUiState = vielModel.listaRefeicaoUiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    if (listaRefeicaoUiState.value.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(247, 251, 252))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Menu(navController)
            Text(
                text = "Refeições",
                fontFamily = MavenPro,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90),
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                BarraPesquisa(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onTextChange = {
                        searchQuery = it
                        vielModel.getRefeicoesNameFilter(searchQuery)
                    }
                )
                GridReceita(listaRefeicaoUiState.value.refeicoes)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListaRefeicaoPreview() {
    VitalisAppTheme {
        Refeicoes(ListaRefeicaoViewModel(), rememberNavController())
    }
}