package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ConfirmacaoParq : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Parq(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Parq(name: String, modifier: Modifier = Modifier) {

//    showDialog: Boolean,
//    onDismiss: () -> Unit,
//    onAcceptRisks: () -> Unit,
//    onTakeItEasy: () -> Unit
//    ) {
        var showDialog by remember { mutableStateOf(true) }
    val contexto = LocalContext.current

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .wrapContentSize(Alignment.Center)
                ) {
                    Card(
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(16.dp)
                            .wrapContentSize()
                            .border(4.dp, Color(72, 183, 90))
                            .clip(RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.Black)
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Cuidado com sua Saúde!",
                                color = Color.White,
                                fontFamily = MavenPro,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Percebemos que você tem uma condição de saúde que merece atenção. ",
                                color = Color.White,
                                fontFamily = MavenPro,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Para sua segurança, é importante evitar atividades físicas intensas sem orientação profissional.",
                                color = Color.White,
                                fontFamily = MavenPro,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Recomendamos consultar um Personal Trainer para um plano seguro e eficaz.",
                                color = Color.White,
                                fontFamily = MavenPro,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Como deseja prosseguir?",
                                color = Color.White,
                                fontFamily = MavenPro,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        showDialog = false
                                        // Lógica para aceitar riscos
                                        val login = Intent(contexto, Login::class.java)
                                        contexto.startActivity(login)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(72, 183, 90)
                                    ),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Aceito os riscos",
                                        fontFamily = MavenPro,
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        showDialog = false
                                        // Lógica para pegar mais leve
                                        val login = Intent(contexto, Login::class.java)
                                        contexto.startActivity(login)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0, 122, 255)
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Quero pegar mais leve",
                                        fontFamily = MavenPro,
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview6() {
    VitalisAppTheme {
        Parq("Android")
    }
}