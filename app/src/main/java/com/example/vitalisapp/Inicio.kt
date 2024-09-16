package com.example.vitalisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Home(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Menu()
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
                .padding(top = 24.dp, bottom = 5.dp, start = 14.dp, end = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black.copy(alpha = 0.5f))
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(id = R.mipmap.lembrete),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lembretes",
                    fontFamily = MavenPro,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(5.dp))

                CardLembrete("Conteúdo do lembrete")

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(72, 183, 90)
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Adicionar Lembrete",
                        fontFamily = MavenPro,
                        color = Color.White)
                }
            }
        }
        Atividade()
    }
}

@Composable
fun CardLembrete(content: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = content,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(
                onClick = {}
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.menu),
                    contentDescription = "Mais",
                    modifier = Modifier.size(20.dp)
                )
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
                .background(Color(18,18,19), shape = RoundedCornerShape(24.dp)),
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
    var mostrarCardSuspenso by remember { mutableStateOf(false) }
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
                        painter = painterResource(id = R.mipmap.exerciciopreto),
                        contentDescription = "Exercicio",
                        modifier = Modifier.size(28.dp)
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
                    onClick = {
                        mostrarCardSuspenso = !mostrarCardSuspenso
                    },
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

        if (mostrarCardSuspenso) {
            CardSuspenso()
        }
    }
}

@Composable
fun AvisoMural() {
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
            onClick = { /* Handle button click */ },
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

@Composable
fun CardSuspenso() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.foto),
                contentDescription = "Exercicio",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.exerciciopreto),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = "Exercício:",
                color = Color(72, 183, 90),
                fontSize = 15.sp,
                fontFamily = MavenPro,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Crucifixo",
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = MavenPro,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        Column(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
            Text(
                text = "Descrição:",
                color = Color.Black,
                fontFamily = MavenPro,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Este exercício é bom para as costas",
                color = Color.Black,
                fontFamily = MavenPro,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(10.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            ExercicioCardInfo(titulo = "10:30", subtitulo = "Tempo de execução")
            ExercicioCardInfo(titulo = "5", subtitulo = "Número de repetições")
            ExercicioCardInfo(titulo = "10", subtitulo = "Número de séries")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(72, 183, 90))
            ) {
                Text("Ir para treino")
            }
            Spacer(modifier = Modifier.width(14.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(27,112,202))
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.certos),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Feito")
            }
        }
    }
}

@Composable
fun ExercicioCardInfo(titulo: String, subtitulo: String) {
    Card(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                fontFamily = MavenPro,
                color = Color(72, 183, 90),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = subtitulo,
                fontFamily = MavenPro,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview17() {
    VitalisAppTheme {
        Home("Android")
    }
}