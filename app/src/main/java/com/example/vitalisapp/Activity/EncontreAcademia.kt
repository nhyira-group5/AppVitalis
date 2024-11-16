package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.R
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.ViewModel.EncontrePersonalViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class EncontrePersonal : ComponentActivity() {
    private val viewModel by viewModels<EncontrePersonalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BuscaPersonal(
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
fun BuscaPersonal(
    viewModel: EncontrePersonalViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val enderecoPersonalUiState = viewModel.encontrePersonalUiState.collectAsState()

    if (enderecoPersonalUiState.value.isLoading) {
        LoadingScreen()
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Menu(navController)
                Text(
                    text = stringResource(R.string.encontre),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            // trocar pelo "Pagamento Ativo" do usuário logado
            if (!SessionLogin.pagamentoAtivo!!) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    border = BorderStroke(1.dp, color = Color.Transparent),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Image(
                                painterResource(R.mipmap.cadeado),
                                contentDescription = "Cadeado",
                                modifier = Modifier
                                    .size(90.dp)
                            )

                            Text(
                                text = "Tela Bloqueada!",
                                fontWeight = FontWeight.Bold,
                                fontFamily = MavenPro,
                                fontSize = 25.sp,
                                color = colorResource(R.color.green_400)
                            )

                            Text(
                                text = "Para acessar essa funcionalidade, é necessário garantir nosso plano vip!",
                                fontFamily = MavenPro,
                                fontSize = 17.sp,
                                textAlign = TextAlign.Center
                            )

                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(R.color.green_400),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Ir para tela de planos",
                                )
                            }
                        }
                    }
                }
            } else {
                items(items = enderecoPersonalUiState.value.trainers!!) { trainer ->
                    PersonalCard(trainer) // Fututamente implementar endereço
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EncontrePersonalPreview() {
    VitalisAppTheme {
        BuscaPersonal(EncontrePersonalViewModel(), rememberNavController())
    }
}