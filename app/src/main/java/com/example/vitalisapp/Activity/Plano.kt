package com.example.vitalisapp.Activity

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Plano : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPlano(
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
fun TelaPlano(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.fundoplano),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Menu(navController)
            Text(
                text = "Planos Vitalis",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(72, 183, 90),
                textAlign = TextAlign.Center
            )
            CurrentPlanCard()
            VivaPlanCard()
            Image(
                painter = painterResource(id = R.mipmap.vitalisbranco),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(556.dp, 290.dp)
            )
        }
    }
}

@Composable
fun CurrentPlanCard() {
    Card(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column {
                Text(
                    text = "Plano atual - Gratuito",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(72, 183, 90),
                )
                Text(
                    text = "Benefícios Atuais",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Row {
                    Image(
                        painter = painterResource(id = R.mipmap.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Plano de exercícios de acordo com seu objetivo;" ,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.mipmap.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mural - Espaço para você registrar seu avanço corporal;",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                Row {
                    Image(
                        painter = painterResource(id = R.mipmap.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Acesso a sua rotina (calendário, guia de alimentação, e seus exercícios do dia);",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                Row {
                    Image(
                        painter = painterResource(id = R.mipmap.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Opções de Alimentação",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.mipmap.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Personais e Academias perto de você",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(72, 183, 90)
                    )) {
                    Text(
                        text = "Estou satisfeito",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun VivaPlanCard() {
    Card(
        modifier = Modifier
            .padding(top = 28.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Plano viva Vitalis",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color =  Color(72, 183, 90),
            )
            Text(
                text = "Além dos benefícios atuais que você já possui, você terá acesso à:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 36.dp)
            )
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.check),
                    contentDescription = "check",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Chat com Personal Trainer de sua escolha;",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }

            Row {
                Image(
                    painter = painterResource(id = R.mipmap.check),
                    contentDescription = "check",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Acompanhamento personalisado com o personal para chegar mais perto de seu ojetivo!",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }


            Row(
                modifier = Modifier.padding(top = 48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Por:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(72, 183, 90),
                )
                Text(
                    text = "49,99",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(72, 183, 90),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Text(
                text = "Por Mês",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color =  Color(72, 183, 90),
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(top = 36.dp)
                    .width(277.dp)
                    .height(72.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(72, 183, 90)
                )
            ) {
                Text(
                    text = "Adquirir Plano",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "O plano pode ser cancelado a qualquer momento em nossa plataforma",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview15() {
    VitalisAppTheme {
        TelaPlano("Android", rememberNavController())
    }
}