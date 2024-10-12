package com.example.vitalisapp.Activity

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
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
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BuscaAcademia(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    var cep by remember { mutableStateOf(TextFieldValue("")) }
    var bairro by remember { mutableStateOf(TextFieldValue("")) }
    var rua by remember { mutableStateOf(TextFieldValue("")) }
    var cidade by remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Menu(navController)
        }

        item {
            Text(
                text = stringResource(R.string.encontre),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            PersonalCard(
                nome = "Thiago",
                especialidade = "Emagrecimento",
                endereco = "Itaquera"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview11() {
    VitalisAppTheme {
        BuscaAcademia("Android", rememberNavController())
    }
}