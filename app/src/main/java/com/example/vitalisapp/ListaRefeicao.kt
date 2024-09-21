package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ListaRefeicao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Refeicao(
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
fun Refeicao(name: String, navController: NavHostController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 251, 252))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Menu(navController)
        Text(
            text = "Refeição",
            fontFamily = MavenPro,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(72, 183, 90),
            letterSpacing = 0.5.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BarraPesquisa(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            IngredienteDropdown(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }

        GridReceita()
    }
}

@Composable
fun BarraPesquisa(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color(255, 255, 255))
            .border(2.dp, Color(0, 0, 0), RoundedCornerShape(30.dp)),
        placeholder = { Text("Pesquise uma refeição!",
            fontFamily = MavenPro,) },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.mipmap.lupa),
                contentDescription = "Lupa",
                modifier = Modifier.size(25.dp)
            )
        }
    )
}

@Composable
fun IngredienteDropdown(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
                .border(2.dp, Color(0, 0, 0), RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(255, 255, 255))
        ) {
            Text(
                "Ingrediente",
                fontFamily = MavenPro,
                color = Color(113, 113, 122),
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.mipmap.lupa),
                contentDescription = "Lupa",
                modifier = Modifier.size(25.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Adicione os itens do dropdown aqui
        }
    }
}

@Composable
fun GridReceita() {
    val contexto = LocalContext.current
    val receitas = listOf(
        "Torta de frango", "Brigadeiro fit", "Linguiça recheada com queijo",
        "Salada cezar", "Refogado de panela", "Escondidinho",
        "Virada paulista", "Macarrão com frango", "Frango grelhado",
        "Mousse de maracuja", "Sanduiche integral de frango", "Strogonoff",
        "Sopa de legumes", "Suco de laranja com torrada de ricota", "Pão com ovo"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(receitas) { receita ->
            CardReceita(receita){
                val receita = Intent(contexto, Receita::class.java)
                contexto.startActivity(receita)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview12() {
    VitalisAppTheme {
        Refeicao("Android", rememberNavController())
    }
}