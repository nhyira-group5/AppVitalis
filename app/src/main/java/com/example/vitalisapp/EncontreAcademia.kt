package com.example.vitalisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class EncontreAcademia : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BuscaAcademia(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BuscaAcademia(name: String, modifier: Modifier = Modifier) {
    var cep by remember { mutableStateOf(TextFieldValue("")) }
    var bairro by remember { mutableStateOf(TextFieldValue("")) }
    var rua by remember { mutableStateOf(TextFieldValue("")) }
    var cidade by remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Menu()
        }

        item {
            Text(
                text = "Encontre uma academia",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Row(modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text(
                        text = "CEP",
                        color = Color(0xFF1A1A1A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = cep,
                        onValueChange = { cep = it },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.fillMaxWidth().height(40.dp),
                        placeholder = { Text(text = "CEP", fontSize = 10.sp, color = Color.Gray) }
                    )
                }

                Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                    Text(
                        text = "Bairro",
                        color = Color(0xFF1A1A1A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = bairro,
                        onValueChange = { bairro = it },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.fillMaxWidth().height(40.dp),
                        placeholder = { Text(text = "Bairro", fontSize = 10.sp, color = Color.Gray) }
                    )
                }
            }
        }

        item {
            Row(modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text(
                        text = "Rua",
                        color = Color(0xFF1A1A1A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = rua,
                        onValueChange = { rua = it },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.fillMaxWidth().height(40.dp),
                        placeholder = { Text(text = "Rua", fontSize = 10.sp, color = Color.Gray) }
                    )
                }

                Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                    Text(
                        text = "Cidade",
                        color = Color(0xFF1A1A1A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = cidade,
                        onValueChange = { cidade = it },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.fillMaxWidth().height(40.dp),
                        placeholder = { Text(text = "Cidade", fontSize = 10.sp, color = Color.Gray) }
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(72, 183, 90))
                ) {
                    Text("Buscar", color = Color.White)
                }
            }
        }

        item {
            Image(
                painter = painterResource(id = R.mipmap.mapa),
                contentDescription = "Mapa",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        item {
            AcademiaCard(
                nome = "Academia Gaviões",
                avaliacao = "4.7",
                horario = "Seg a Sex de 06h30 às 23h00; Sáb., Dom. e Feriados de 09h30 às 18h00",
                endereco = "Rua dos Artistas, 416",
                tempoCaminhada = "10 min de caminhada"
            )
        }

        item {
            PersonalCard(
                nome = "Thiago",
                especialidade = "Emagrecimento",
                endereco = "Itaquera"
            )
        }
    }
}

@Composable
fun AcademiaCard(
    nome: String,
    avaliacao: String,
    horario: String,
    endereco: String,
    tempoCaminhada: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nome,
                        fontSize = 20.sp,
                        color = Color(72, 183, 90),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.estrela),
                            contentDescription = "Estrela",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = avaliacao,
                            fontSize = 16.sp,
                            color = Color(255, 255, 0),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = horario,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = endereco,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = tempoCaminhada,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
    Text(
        text = "Encontre um personal",
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(72, 183, 90),
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun PersonalCard(nome: String, especialidade: String, endereco: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.mipmap.usuarioperfil),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = nome,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(72, 183, 90)
                )
                Text(
                    text = especialidade,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = endereco,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview11() {
    VitalisAppTheme {
        BuscaAcademia("Android")
    }
}