package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Receita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Alimento(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Alimento(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 80.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Button(onClick = {
            val listaRefeicao = Intent(contexto, ListaRefeicao::class.java)
            contexto.startActivity(listaRefeicao)
        },
            colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                containerColor = Color.Transparent),
            modifier = Modifier.padding(bottom = 10.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.voltar),
                contentDescription = "Voltar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Voltar",
                fontFamily = MavenPro,)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Torta de Frango",
            fontFamily = MavenPro,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(72, 183, 90)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Home > Refeição > Torta de Frango",
            fontFamily = MavenPro,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(72, 183, 90)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Comida()
        Spacer(modifier = Modifier.height(10.dp))
        PaginaReceita()
    }
}

@Composable
fun Comida() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color(211, 211, 211), RoundedCornerShape(16.dp))
            .background(Color(255, 255, 255)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color(255, 255, 255))
        ) {
            Image(
                painter = painterResource(id = R.mipmap.tortadefrango),
                contentDescription = "Imagem da Receita",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun PaginaReceita() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color(245, 245, 245))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Ingredientes",
                    fontFamily = MavenPro,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                val ingredientes = listOf(
                    "Recheio",
                    "500 g de peito de frango sem pele",
                    "1/2 litro de caldo de galinha",
                    "4 colheres (sopa) de óleo",
                    "1 dente de alho amassado",
                    "1 cebola picada",
                    "3 tomates sem pele e sem sementes",
                    "1 xícara (chá) de ervilhas",
                    "Sal a gosto",
                    "Pimenta-do-reino a gosto",
                    "Massa",
                    "250 ml de leite",
                    "3/4 de xícara (chá) de óleo",
                    "2 ovos",
                    "1 e 1/2 xícara (chá) de farinha de trigo",
                    "sal a gosto",
                    "1 colher (sopa) de fermento em pó",
                    "queijo ralado a gosto"

                )
                ingredientes.forEach { ingrediente ->
                    Text(text = "• $ingrediente",
                        fontFamily = MavenPro,)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Modo de Preparo",
                    fontFamily = MavenPro,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                val passos = listOf(
                    "Cozinhe o peito de frango no caldo até ficar macio.",
                    "Separe 1 xícara (chá) de caldo do cozimento e reserve.",
                    "Refogue os demais ingredientes e acrescente as ervilhas por último.",
                    "Desfie o frango, misture ao caldo e deixe cozinhar até secar."
                )
                passos.forEachIndexed { index, passo ->
                    Text(text = "${index + 1}. $passo",
                        fontFamily = MavenPro)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview13() {
    VitalisAppTheme {
        Alimento("Android")
    }
}