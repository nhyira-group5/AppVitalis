package com.example.vitalisapp.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.vitalisapp.DTO.Pagamento.PagamentoCreateEditDto
import com.example.vitalisapp.DTO.Pagamento.PaymentKoinDto
import com.example.vitalisapp.R
import com.example.vitalisapp.View.LoginSession.SessionLogin
import com.example.vitalisapp.ViewModel.PlanoViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class Plano : ComponentActivity() {
    private val viewModel by viewModels<PlanoViewModel>()

    private val paymentScope = getKoin().createScope("paymentSession", named("paymentSession"))
    private val paymentKoin: PaymentKoinDto by paymentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPlano(
                        paymentKoin,
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
fun TelaPlano(
    paymentKoin: PaymentKoinDto,
    viewModel: PlanoViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val planoUiState = viewModel.planoUiState.collectAsState()
    var paymentStatus by remember { mutableStateOf(paymentKoin.paymentComplete) }

    LaunchedEffect(Unit) {
        if (paymentKoin.paymentId != null) viewModel.getPayment(
            paymentKoin.paymentId!!,
            paymentKoin
        )
    }

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Menu(navController)

            Text(
                text = stringResource(R.string.plano),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_300),
                textAlign = TextAlign.Center
            )

            paymentStatus = paymentKoin.paymentComplete
            if (paymentStatus != false) {
                TelaAcoes(navController, viewModel, paymentKoin)
            } else {
                TelaQrCode(paymentKoin)
            }

            Image(
                painter = painterResource(id = R.mipmap.vitalisbranco),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
}

@Composable
fun TelaAcoes(
    navController: NavHostController,
    viewModel: PlanoViewModel,
    paymentKoin: PaymentKoinDto,
) {
    // CARD - Plano Gratuito
    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = "Plano Gratuíto",
            fontFamily = MavenPro,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.green_300)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null,
                painter = painterResource(R.mipmap.check)
            )
            Text(
                text = "Plano de exercícios de acordo com seu objetivo;",
                fontSize = 11.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null,
                painter = painterResource(R.mipmap.check)
            )
            Text(
                text = "Mural - Espaço para você registrar seu avanço corporal;",
                fontSize = 11.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null,
                painter = painterResource(R.mipmap.check)
            )
            Text(
                text = "Acesso a sua rotina (calendário, guia de alimentação, e seus exercícios do dia);",
                fontSize = 11.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null,
                painter = painterResource(R.mipmap.check)
            )
            Text(
                text = "Guia de Alimentação;",
                fontSize = 11.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null,
                painter = painterResource(R.mipmap.check)
            )
            Text(
                text = "Personais e Academias perto de você;",
                fontSize = 11.sp
            )
        }

        if (SessionLogin.pagamentoAtivo!!) {
            Button(
                modifier = Modifier
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.gray_300),
                    contentColor = Color.White
                ),
                onClick = { navController.navigate("home") }
            ) {
                Text(
                    text = "Já está afiliado!",
                    fontSize = 12.sp
                )
            }
        } else {
            Button(
                modifier = Modifier
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green_400),
                    contentColor = Color.White
                ),
                onClick = { navController.navigate("home") }
            ) {
                Text(
                    text = "Este é seu plano atual!",
                    fontSize = 12.sp
                )
            }
        }
    }

    // CARD - Plano Vitalis
    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Plano Vitalis",
                fontFamily = MavenPro,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_300)
            )
            Text(
                modifier = Modifier,
                text = "Além dos benefícios atuais que você já possui, você terá acesso à:",
                fontFamily = MavenPro,
                fontSize = 9.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(25.dp),
                    contentDescription = null,
                    painter = painterResource(R.mipmap.check)
                )
                Text(
                    text = "Chat com Personal Trainer de sua escolha;",
                    fontSize = 11.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(25.dp),
                    contentDescription = null,
                    painter = painterResource(R.mipmap.check)
                )
                Text(
                    text = "Acompanhamento personalisado com o personal para chegar mais perto de seu objetivo!;",
                    fontSize = 11.sp
                )
            }

            Column(
                modifier = Modifier.width(125.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Por:",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.green_300),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "R$49,99",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.green_300)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Mês",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.green_300),
                    textAlign = TextAlign.End
                )
            }
        }

        if (SessionLogin.pagamentoAtivo!!) {
            Button(
                modifier = Modifier
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.gray_300),
                    contentColor = Color.White
                ),
                onClick = { navController.navigate("home") }
            ) {
                Text(
                    text = "Este é seu plano atual!",
                    fontSize = 12.sp
                )
            }
        } else {
            Button(
                modifier = Modifier
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green_300),
                    contentColor = Color.White
                ),
                onClick = {
                    viewModel.createPayment(
                        PagamentoCreateEditDto(SessionLogin.id!!, "PIX", 1),
                        paymentKoin
                    )
                }
            ) {
                Text(
                    text = "Adquirir o plano",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun TelaQrCode(paymentKoin: PaymentKoinDto) {
    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
            .height(425.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (paymentKoin.qrCodeBitmap?.asImageBitmap() == null) {
            AsyncImage(
                model = "https://cdn-icons-png.flaticon.com/512/7068/7068033.png",
                contentDescription = "QR Code do pagamento - Erro",
                modifier = Modifier
                    .size(300.dp)
                //.shadow(elevation = 5.dp)
            )
            Text(
                text = "Erro ao gerar QR Code para o Pagamento!",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.red_error),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Infelizmente ocorreu um problema na geração do QR Code. Entre em contato para suporte: contato.nhyira@gmail.com",
                fontSize = 12.sp
            )
        } else {
            Image(
                bitmap = paymentKoin.qrCodeBitmap!!.asImageBitmap(),
                contentDescription = "QR Code do pagamento",

                modifier = Modifier
                    .size(300.dp)
            )
            Text(
                text = "Pagamento do plano Viva Vitalis de R$49.99 via PIX",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_400),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Após realizar o pagamento, volte para o página inicial",
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPlano() {
    VitalisAppTheme {
        TelaPlano(
            PaymentKoinDto(),
            viewModel<PlanoViewModel>(),
            rememberNavController()
        )
    }
}