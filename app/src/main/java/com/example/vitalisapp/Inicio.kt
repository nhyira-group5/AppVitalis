package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun Home(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    var showCard by remember { mutableStateOf(false) }
    var lembreteContent by remember { mutableStateOf("") }
    val lembretes = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Menu(navController)

        Text(
            text = "Bem vindo(a), $name",
            fontFamily = MavenPro,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 14.dp, end = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lembretes",
                        fontFamily = MavenPro,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.mipmap.botaomural),
                        contentDescription = "Botão",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                showCard = !showCard
                                if (showCard) lembretes.add(lembreteContent)
                            }
                    )
                }

                if (showCard) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        TextField(
                            value = lembreteContent,
                            onValueChange = { lembreteContent = it },
                            label = { Text("Insira seu lembrete") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                        ) {
                          items (lembretes){ content ->
                              CardLembrete(content = content)}
                        }
                    }
                }
            }
        }

        Atividade()
    }
}

@Composable
fun CardLembrete(content: String) {
    var mostrarExcluir by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        IconButton(
            onClick = {
                mostrarExcluir = !mostrarExcluir
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.menu),
                contentDescription = "Mais",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "Aqui parece seu lembrete",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(top = 32.dp)
        )

        if (mostrarExcluir) {
            Card(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.BottomEnd)
                    .width(100.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    elevation = null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Excluir",
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun Atividade() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Atividades de hoje",
            fontSize = 20.sp,
            fontFamily = MavenPro,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 15.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(18, 18, 19), shape = RoundedCornerShape(24.dp)),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Porcentagem(
                icone = R.mipmap.comidappreto,
                valor = "--/10",
                titulo = "Refeições"
            )
            Porcentagem(
                icone = R.mipmap.exerciciopreto,
                valor = "01/10",
                titulo = "Exercícios"
            )
            Porcentagem(
                icone = R.mipmap.calendario,
                valor = "01/03",
                titulo = "Meta Semanal"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardAtividades()
        Spacer(modifier = Modifier.weight(1f))

        AvisoMural()
    }
}

@Composable
fun Porcentagem(icone: Int, valor: String, titulo: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(90.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = icone),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = valor,
            fontFamily = MavenPro,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = titulo,
            fontSize = 12.sp,
            fontFamily = MavenPro,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun CardAtividades() {
    val contexto = LocalContext.current
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.tortadefrango),
                        contentDescription = "Exercicio",
                        modifier = Modifier
                            .size(60.dp)
                            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
                    )
                    Text(
                        text = "Exercício:",
                        fontSize = 18.sp,
                        fontFamily = MavenPro,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(72, 183, 90)
                    )
                    Text(
                        text = "Titulo",
                        fontFamily = MavenPro,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                IconButton(
                    onClick = {val detalheExercicio = Intent(contexto, DetalheExercicio::class.java)
                        contexto.startActivity(detalheExercicio)},
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.seta),
                        contentDescription = "seta",
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AvisoMural() {
    val contexto = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Observe o resultado do seu \nesforço com seu mural de fotos",
            color = Color.Black,
            fontFamily = MavenPro,
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 8.dp)
        )

        Button(
            onClick = {val mural = Intent(contexto, Mural::class.java)
                contexto.startActivity(mural)},
            modifier = Modifier
                .height(40.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(72, 183, 90))
        ) {
            Text(
                text = "Ver mural!",
                fontFamily = MavenPro,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview17() {
    VitalisAppTheme {
        Home("Android", rememberNavController())
    }
}