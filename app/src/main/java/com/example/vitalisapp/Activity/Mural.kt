package com.example.vitalisapp.Activity

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class Mural : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Galeria(
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
fun Galeria(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
        var mostrarExcluir by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBFC))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Menu(navController)
            Text(
                text = "Mural de evolução",
                color = Color(72, 183, 90),
                fontFamily = MavenPro,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.72.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Poste aqui sua evolução durante a sua jornada para uma vida mais saudável!",
                color = Color.Black,
                fontFamily = MavenPro,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            DateInput(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 24.dp)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ImageCard(
                modifier = Modifier.weight(0.58f),
                imageRes = R.mipmap.foto,
                date = "31.12.9999"
            )
            ImageCard(
                modifier = Modifier.weight(0.42f),
                imageRes = R.mipmap.foto,
                date = "31.12.9999"
            )

        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(80.dp)
                .clickable { }
        ) {
            Image(
                painter = painterResource(id = R.mipmap.botaomural),
                contentDescription = "Botão",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun DateInput(modifier: Modifier = Modifier) {
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
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Placeholder",fontFamily = MavenPro) },
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    VitalisAppTheme {
        Galeria("Android", rememberNavController())
    }
}