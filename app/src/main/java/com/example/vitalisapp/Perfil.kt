package com.example.vitalisapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Perfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PerfilUsuario(
                        name = "Android",
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// FAZER COM VIEW MODEL

@Composable
fun PerfilUsuario(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 251, 252))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Menu(navController)
        CartaoInfoPessoal()
        CartaoInfoTecnica()
        CartaoPremium()
//        CartaoPersonal(
//            nome = "Nome do Personal",
//            email = "email@exemplo.com",
//            validadeAfiliacao = "12/2024",
//            username = "personal123",
//            especialidade = "Nutrição",
//            onClickVerPersonal = { /* Ação ao clicar no botão */ }
//        )
    }
}

@Composable
fun CartaoInfoPessoal() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Informações Pessoais",
                fontFamily = MavenPro,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.usuarioperfil),
                    contentDescription = "Foto Usuário",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Image(
                    painter = painterResource(id = R.mipmap.botaomural),
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
                ItemInfoPessoal("Nome completo:", "Marcos da Silva")
                ItemInfoPessoal("E-mail principal:", "FulanoSailva@gmail.com")
                ItemInfoPessoal("E-mail de recuperação:", "FulanoSilvaSalada2@gmail.com")
                ItemInfoPessoal("Nickname:", "marC0S23!")
                ItemInfoPessoal("Data de nascimento:", "17 / 01 / 2005")
                ItemInfoPessoal("Sexo:", "Masculino")
            }
        }
    }
}

fun onEditClick() {
}


@Composable
fun ItemInfoPessoal(label: String, valor: String, modifier: Modifier = Modifier) {
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
fun CartaoInfoTecnica() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(44.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Informações Técnicas",
                    fontFamily = MavenPro,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90)
                )
            }

            var fumante by remember { mutableStateOf(false) }
            var alcoolatra by remember { mutableStateOf(true) }
            var deficiente by remember { mutableStateOf(false) }
            var problemaCardiaco by remember { mutableStateOf(false) }

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = fumante,
                        onCheckedChange = { fumante = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(72, 183, 90),
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Fumante",
                        fontFamily = MavenPro,
                        fontSize = 18.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = alcoolatra,
                        onCheckedChange = { alcoolatra = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(72, 183, 90),
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Alcoólatra",
                        fontFamily = MavenPro,
                        fontSize = 18.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = deficiente,
                        onCheckedChange = { deficiente = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(72, 183, 90),
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Deficiente",
                        fontFamily = MavenPro,
                        fontSize = 18.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = problemaCardiaco,
                        onCheckedChange = { problemaCardiaco = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(72, 183, 90),
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Problemas Cardíacos",
                        fontFamily = MavenPro,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Peso: 79 KG",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )
                Text(
                    text = "Altura: 1.83 M",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Meta: Perda de peso",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "P.C",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )
                Text(
                    text = "Lorem Ipsum; Lorem Ipsum; Lorem Ipsum; Lorem Ipsum ...",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Deficiente",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )
                Text(
                    text = "Lorem Ipsum; Lorem Ipsum; Lorem Ipsum; Lorem Ipsum ...",
                    fontFamily = MavenPro,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun CartaoPremium() {
    val contexto = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Personal Afiliado",
                    fontFamily = MavenPro,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90),
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.mipmap.cadeado),
                    contentDescription = "Ícone Premium",
                    modifier = Modifier.size(60.dp)
                )
            }
            Text(
                text = "Este é um recurso PAGO!\n\nPara acessá-lo, é necessário participar do plano premium. Contribua para sua experiência premium como usuário e se mantenha melhor do que nunca!",
                fontFamily = MavenPro,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Button(
                onClick = { val plano = Intent(contexto, Plano::class.java)
                    contexto.startActivity(plano)},
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(22, 101, 52))
            ) {
                Text(
                    text = "Ser Premium",
                    fontFamily = MavenPro,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
    // @Composable
    // fun CartaoPersonal(nome: String,
//                       email: String,
//                       validadeAfiliacao: String,
//                       username: String, especialidade:
//                       String, onClickVerPersonal: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    //text = nome,
                    text = "Will Dantas Gilherme da Silva",
                    fontFamily = MavenPro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    //text = "E-mail: $email",
                    text = "E-mail: will@gmail.com",
                    fontFamily = MavenPro,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    //text = "Validade de afiliação: $validadeAfiliacao",
                    text = "Validade de afiliação: 17/01/2024 à 31/12/2024",
                    fontFamily = MavenPro,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    //text = "Username: $username",
                    text = "Username: personal20!",
                    fontFamily = MavenPro,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    //text = "Especialidade: $especialidade",
                    text = "Especialidade: Perda de Peso",
                    fontFamily = MavenPro,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    //onClick = onClickVerPersonal,
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            72,
                            183,
                            90
                        )
                    )
                ) {
                    Text(text = "Ver Personal",
                        fontFamily = MavenPro,
                        color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.usuarioperfil),
                    contentDescription = "Imagem do Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun GreetingPreview9() {
    VitalisAppTheme {
        PerfilUsuario("Android", rememberNavController())
    }
}