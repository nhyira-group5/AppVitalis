package com.example.vitalisapp.Activity

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.vitalisapp.DTO.Refeicao.RefeicaoExibitionDto
import com.example.vitalisapp.DTO.Treino.TreinoExibitionDto
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro

// Teste tela de carregamento
@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Carregando...", fontSize = 24.sp)
    }
}

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
            contentDescription = "Sessão Atual: ",
            modifier = Modifier
                .size(60.dp)
                .clickable { expanded = !expanded }
        )

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(animationSpec = tween(durationMillis = 500)) + fadeIn(),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 400)) + fadeOut()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color(72, 183, 90), shape = RoundedCornerShape(12.dp))
                    .padding(6.dp)
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
                        .clickable { navController.navigate("chatPersonal") }
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
fun DailyActivities(
    trainings: MutableList<TreinoExibitionDto>?,
    meals: MutableList<RefeicaoExibitionDto>?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Text(
                text = "Atividades do Dia",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = colorResource(R.color.green_300)
            )
        }
        if (trainings != null) {
            items(items = trainings) { item ->
                CardAtividades(item)
            }
        }

        if (meals != null) {
            items(items = meals) { item ->
                CardAtividades(item)
            }
        }
    }
}

@Composable
fun KpiHome(
    refeicoesTotaisDiaria: Int, refeicoesConcluidasDiaria: Int,
    treinosTotaisDiaria: Int, treinosConcluidosDiaria: Int,
    rotinasDiariasTotaisSemana: Int, rotinasDiariasConcluidasSemana: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.atividade),
            fontSize = 20.sp,
            fontFamily = MavenPro,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(18, 18, 19), shape = RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Porcentagem(
                icone = R.mipmap.comidappreto,
                valor = "$refeicoesConcluidasDiaria/$refeicoesTotaisDiaria",
                titulo = "Refeições"
            )
            Porcentagem(
                icone = R.mipmap.exerciciopreto,
                valor = "$treinosConcluidosDiaria/$treinosTotaisDiaria",
                titulo = "Exercícios"
            )
            Porcentagem(
                icone = R.mipmap.calendario,
                valor = "$rotinasDiariasConcluidasSemana/$rotinasDiariasTotaisSemana",
                titulo = "Meta Semanal"
            )
        }
    }
}

@Composable
fun Porcentagem(icone: Int, valor: String, titulo: String) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .size(90.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)),
        //.shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = icone),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = valor,
            fontFamily = MavenPro,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Text(
            text = titulo,
            fontSize = 12.sp,
            fontFamily = MavenPro,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

// Para treino
@Composable
fun CardAtividades(
    treino: TreinoExibitionDto
) {
    val contexto = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    model = treino.exercicio.midias.find { it.tipo == "Imagem" }!!.caminho,
                    contentDescription = "Foto do exercício ${treino.exercicio.nome}",
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(225.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.exercicio),
                        fontSize = 16.sp,
                        fontFamily = MavenPro,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(72, 183, 90)
                    )
                    Text(
                        text = treino.exercicio.nome,
                        fontFamily = MavenPro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
            IconButton(
                onClick = {
                    val detalheExercicio = Intent(contexto, DetalheExercicio::class.java)
                    detalheExercicio.putExtra("ID_EXERCICIO", treino.exercicio.idExercicio)
                    contexto.startActivity(detalheExercicio)
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
}

@Composable
fun CardAtividades(
    refeicao: RefeicaoExibitionDto
) {
    val contexto = LocalContext.current

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
                AsyncImage(
                    model = refeicao.midias?.find { it.tipo == "Imagem" }!!.caminho,
                    contentDescription = "Foto da refeição ${refeicao.nome}",
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp))
                )
                Text(
                    text = stringResource(R.string.refeicao),
                    fontSize = 18.sp,
                    fontFamily = MavenPro,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90)
                )
                Text(
                    text = refeicao.nome!!,
                    fontFamily = MavenPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            IconButton(
                onClick = {
                    val detalheRefeicao = Intent(contexto, DetalheRefeicao::class.java)
                    detalheRefeicao.putExtra("ID_REFEICAO", refeicao.idRefeicao)
                    contexto.startActivity(detalheRefeicao)
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
fun BotaoConcluido() {
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

@Composable
fun UserCard(user: String, meta: String, numero: Int, imagemUSer: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imagemUSer),
                contentDescription = "Foto Usuário",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = user,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(134, 86, 169)
                )
                Row {
                    Text(text = "Meta:", color = Color(134, 86, 169))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = meta,
                        fontWeight = FontWeight.Bold,
                        color = Color(24, 24, 27)
                    )
                }
                Text(
                    text = "Você tem ${numero} novas mensagens",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
fun ExercicioItem(exercicios: List<String>) {
    val contexto = LocalContext.current

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

@Composable
fun GridReceita(receitas: List<String>) {
    val contexto = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(receitas) { receita ->
            CardReceita(receita) {
                val receita = Intent(contexto, DetalheRefeicao::class.java)
                contexto.startActivity(receita)
            }
        }
    }
}

@Composable
fun BarraPesquisa(modifier: Modifier = Modifier, onTextChange: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onTextChange(it)
        },
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color(255, 255, 255))
            .border(2.dp, Color(0, 0, 0), RoundedCornerShape(30.dp)),
        placeholder = {
            Text(
                stringResource(R.string.pesquisa),
                fontFamily = MavenPro,
            )
        },
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
fun DateInput(modifier: Modifier = Modifier, onTextChange: (String) -> Unit) {
    var searchDate by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Text(
            text = "Data",
            color = Color(0xFF1A1A1A),
            fontFamily = MavenPro,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.6.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        OutlinedTextField(
            value = searchDate,
            onValueChange = {
                searchDate = it
                onTextChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Data", fontFamily = MavenPro) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.mipmap.calendario),
                    contentDescription = "Calendário",
                    modifier = Modifier.size(25.dp)
                )
            },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun ItemInfo(label: String, valor: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontFamily = MavenPro,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = valor,
            fontFamily = MavenPro,
            fontSize = 16.sp,
            color = Color(24, 24, 27),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun CartaoInfo(
    tipoUsuario: String,
    imagemUsuario: Int,
    nome: String,
    email: String,
    emailRecuperacao: String = "",
    nickname: String,
    aniversario: String,
    sexo: String,
    especialidade: String = "",
    graduacao: String = "",
    onEditClick: () -> Unit
) {

    val corTexto = if (tipoUsuario == "personal") Color(134, 86, 169) else Color(72, 183, 90)
    val corBotao = if (tipoUsuario == "personal") R.mipmap.botaopersonal else R.mipmap.botaomural

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.info_pessoal),
                fontFamily = MavenPro,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = corTexto,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imagemUsuario),
                    contentDescription = "Foto Usuário",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Image(
                    painter = painterResource(id = corBotao),
                    contentDescription = "Botão",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .clickable { onEditClick() }
                        .align(Alignment.BottomEnd)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                ItemInfo("Nome completo:", nome)
                ItemInfo("E-mail principal:", email)
                ItemInfo("Nickname:", nickname)
                ItemInfo("Data de nascimento:", aniversario)
                ItemInfo("Sexo:", sexo)


                if (tipoUsuario == "personal") {
                    ItemInfo("Especialidade:", especialidade)
                    ItemInfo("Data de formação:", graduacao)
                }

                if (tipoUsuario == "usuario") {
                    ItemInfo("E-mail de recuperação:", emailRecuperacao)
                }
            }
        }
    }
}

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    isPersonal: Boolean,
    modifier: Modifier = Modifier
) {
    val checkboxColor = if (isPersonal) Color(168, 123, 199) else Color(72, 183, 90)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = checkboxColor,
                uncheckedColor = Color.White
            )
        )
        Text(
            text = label,
            fontFamily = MavenPro,
            color = Color.White
        )
    }
}