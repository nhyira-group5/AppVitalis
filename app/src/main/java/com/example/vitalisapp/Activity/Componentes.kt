package com.example.vitalisapp.Activity

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro

@Composable
fun Menu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.areausuario),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable { expanded = !expanded }
        )

        if (expanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color(72, 183, 90), shape = RoundedCornerShape(12.dp))
                    .padding(vertical = 4.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.perfil),
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("perfil") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("home") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.exercicio),
                    contentDescription = "Exercícios",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("exercicios") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.comidamenu),
                    contentDescription = "Refeição",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("refeicao") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.users),
                    contentDescription = "Personais",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("busca") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.chat),
                    contentDescription = "Chat",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("chat") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.images),
                    contentDescription = "Imagens",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("galeria") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.coin),
                    contentDescription = "Planos",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("planos") }
                )
            }
        }
    }
}

@Composable
fun MenuPersonal(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.userareapersonal),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable { expanded = !expanded }
        )

        if (expanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color(80, 52, 101), shape = RoundedCornerShape(12.dp))
                    .padding(vertical = 4.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.userareapersonal),
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("perfilPersonal") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("homePersonal") }
                )
                Image(
                    painter = painterResource(id = R.mipmap.chat),
                    contentDescription = "Chat",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)
                        .clickable {navController.navigate("chatPersonal") }
                )
            }
        }
    }
}

@Composable
fun InputText(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, fontFamily = MavenPro, color = Color.White) },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ImageCard(modifier: Modifier = Modifier, imageRes: Int, date: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
        ) {

            Image(
                painter = painterResource(id = R.mipmap.foto),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )

            Image(
                painter = painterResource(id = R.mipmap.pin),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-12).dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = date,
            fontFamily = MavenPro,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun CardReceita(recipeName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(260.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color(211, 211, 211), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(Color(255, 255, 255)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.foto),
                contentDescription = "Imagem da Receita",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = recipeName,
                color = Color(24, 24, 27),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CardConversa(
    imagem: Int,
    nome: String,
    mensagem: String,
    hora: String,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(5.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row {
                Image(
                    painter = painterResource(id = imagem),
                    contentDescription = null,
                    modifier = Modifier
                        .size(57.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = nome,
                        color = Color.Black,
                        fontFamily = MavenPro,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = mensagem,
                        color = Color.Black,
                        fontFamily = MavenPro,
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = hora,
                        fontFamily = MavenPro,
                        color = Color.Gray,
                        fontSize = 5.sp
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
            text = stringResource(R.string.atividade),
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
        CardAtividades(titulo="Título")
        Spacer(modifier = Modifier.weight(1f))
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
fun CardAtividades( titulo: String) {
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
                        text = stringResource(R.string.exercicio),
                        fontSize = 18.sp,
                        fontFamily = MavenPro,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(72, 183, 90)
                    )
                    Text(
                        text = titulo,
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