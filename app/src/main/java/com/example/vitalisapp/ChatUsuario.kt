package com.example.vitalisapp

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class ChatUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatUser(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

    @Composable
    fun ChatUser(name: String, modifier: Modifier = Modifier) {
        val especialidade = "Especialista em emagrecimento"

        val mensagens = listOf(
            Mensagem("Oi, tudo bem?", true),
            Mensagem("Tudo ótimo, e você?", false),
            Mensagem("Estou bem, obrigado!", true),
            Mensagem("", false),
            Mensagem("", true)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.voltar),
                        contentDescription = "Voltar",
                        modifier = Modifier.size(48.dp)
                    )
                    Image(
                        painter = painterResource(id = R.mipmap.foto),
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(67.dp)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(
                            text = name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = especialidade,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Color.Red)
                            )
                            Text(
                                text = "Status: Não afiliado",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(mensagens) { message ->
                    QuandoMensagem(message)
                }
            }

            MensagemInput()
        }
    }

    @Composable
    fun QuandoMensagem(message: Mensagem) {
        if (message.isFromUser) {
            MensagemUsuario(text = message.text)
        } else {
            MensagemOutraPessoa(text = message.text)
        }
    }

    @Composable
    fun MensagemUsuario(text: String) {
        Row(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, end = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(100, 194, 115))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.enviado),
                            contentDescription = "Enviado",
                            modifier = Modifier.size(10.dp)
                        )
                        Text(
                            text = "00:00",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun MensagemOutraPessoa(text: String) {
        Row(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(134, 86, 169))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.enviado),
                            contentDescription = "Enviado",
                            modifier = Modifier.size(10.dp)
                        )
                        Text(
                            text = "00:00",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun MensagemInput() {
        var mensagem by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = mensagem,
                onValueChange = { mensagem = it },
                modifier = Modifier
                    .weight(1f)
                    .height(59.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .background(Color.Transparent),
                placeholder = { Text("Mensagem para Marcelo da Silva") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.1.sp,
                    color = Color(0xFF71717A)
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.clip),
                    contentDescription = "Anexo",
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(34, 197, 94)),
                modifier = Modifier
                    .width(123.dp)
                    .height(44.dp)
            ) {
                Text(
                    text = "Enviar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview20() {
    VitalisAppTheme {
        ChatUser("Android")
    }
}