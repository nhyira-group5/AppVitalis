package com.example.vitalisapp.Activity

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class DetalheExercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VerExercicio(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun VerExercicio(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 80.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Button(onClick = {
            val listaExercicio = Intent(contexto, ListaExercicio::class.java)
            contexto.startActivity(listaExercicio)
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
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Crucifixo",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(72, 183, 90)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Home > Treinos > Crucifixo",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(72, 183, 90)
        )
        Spacer(modifier = Modifier.height(10.dp))

        VideoExercicio()

        Execucao()

        Informacoes()

        Botao()
    }
}

@Composable
fun VideoExercicio() {
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
                painter = painterResource(id = R.mipmap.video),
                contentDescription = "Imagem da Receita",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun Informacoes() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Descrição",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry...",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TagsLayout(
            tags = listOf("#peito", "#costas", "#bíceps", "#tríceps", "#ombro", "#perna"),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TagsLayout(tags: List<String>, modifier: Modifier = Modifier) {
    var currentRowWidth = 0
    val maxRowWidth = 500

    Column(modifier = modifier) {
        var rowTags = mutableListOf<String>()

        tags.forEach { tag ->
            val tagWidth = tag.length * 14

            if (currentRowWidth + tagWidth > maxRowWidth) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    rowTags.forEach {
                        Tag(text = it)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                rowTags.clear()
                currentRowWidth = 0
            }

            rowTags.add(tag)
            currentRowWidth += tagWidth + 16
        }

        if (rowTags.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                rowTags.forEach {
                    Tag(text = it)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .border(1.dp, Color(72, 183, 90), shape = RoundedCornerShape(16.dp))
            .background(Color(241, 241, 241), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = Color(72, 183, 90), fontSize = 12.sp)
    }
}

@Composable
fun Execucao() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Informações de execução",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    InfoCard(title = "Tempo de execução", value = "2 minutos e 10 segundos")
                    InfoCard(title = "Número de repetições", value = "15 repetições")
                    InfoCard(title = "Número de séries", value = "5 séries de repetições")
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(300.dp)
            .height(75.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = value,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun Botao() {
    var isCompleted by remember { mutableStateOf(false) }

    Button(
        onClick = { isCompleted = !isCompleted },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isCompleted) Color(72, 183, 90) else Color(27, 112, 202)
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.certos),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if (isCompleted) "Concluído" else "Marcar como concluído",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview14() {
    VitalisAppTheme {
        VerExercicio("Android")
    }
}