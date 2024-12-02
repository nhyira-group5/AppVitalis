package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.ViewModel.HomePersonalViewModel
import com.example.vitalisapp.ViewModel.HomeViewModel
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class HomePersonal : ComponentActivity() {
    private val viewModel by viewModels<HomePersonalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomePersonal(
                        viewModel,
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomePersonal(
    viewModel: HomePersonalViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val homeUiState by viewModel.homePersonalUiState.collectAsState()

    val afiliados = homeUiState.afiliados ?: emptyList()
    val contratos = homeUiState.contratos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuPersonal(navController)
        Text(
            text = stringResource(R.string.Bem_vindo, SessionLogin.nickName ?: "Guest"),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.filiado),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0, 0, 0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
//
                        if (afiliados != null) {
                            items(items = afiliados) { item ->
                                UserCard(
                                    nick = item.nickname ?: "Sem nickname",
                                    meta = item.meta?.toString() ?: "Meta não definida",
                                    imagemUser = item.midia?.caminho?.toString() ?: ""
                                )
                            }
                        }
                    }


                }
            }

            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0, 0, 0)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.solicitacao),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(3) {
                            CardAfiliado(
                                nick="nick",
                                nome = "nome",
                                meta = "meta"
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardAfiliado(nick: String, nome: String, meta:String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp, 10.dp, 20.dp, 24.dp)
    ) {
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nick,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF52525B)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = nome,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "Meta:", color = Color(134, 86, 169))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = meta,
                        fontWeight = FontWeight.Bold,
                        color = Color(24, 24, 27)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(208, 54, 54)
                    )
                ) {
                    Text("Negar", color = Color.White)
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(76, 206, 109)
                    )
                ) {
                    Text("Aceitar", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview22() {
    VitalisAppTheme {
        HomePersonal(
            viewModel<HomePersonalViewModel>(),
            rememberNavController()
            )
    }
}