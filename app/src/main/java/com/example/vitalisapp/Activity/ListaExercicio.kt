package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ListaExercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GaleriaExercicio(
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
fun GaleriaExercicio(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Menu(navController)

            Text(
                text = "Exercícios - 13/05/2024",
                fontFamily = MavenPro,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color(72, 183, 90)
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExercicioItem()
        }
    }

    @Composable
    fun ExercicioItem() {
        val contexto = LocalContext.current
        val exercicios = listOf(
            "Agachamento", "Flexão de braço", "Abdominal", "Burpee",
            "Prancha", "Elevação lateral", "Corrida no lugar", "Pular corda",
            "Afundo", "Polichinelo", "Mountain climber", "Supino",
            "Levantamento terra", "Extensão de tríceps", "Rosca bíceps"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(exercicios) { exercicio ->
                CardReceita(exercicio) {
                    val detalheExercicio = Intent(contexto, DetalheExercicio::class.java)
                    contexto.startActivity(detalheExercicio)
                }
            }
        }
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview16() {
    VitalisAppTheme {
        GaleriaExercicio("Android", rememberNavController())
    }
}