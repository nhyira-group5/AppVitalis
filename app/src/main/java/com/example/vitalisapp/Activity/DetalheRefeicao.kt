package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vitalisapp.DTO.Refeicao.AlimentoPorRefeicaoDto
import com.example.vitalisapp.DTO.Refeicao.MidiaDto
import com.example.vitalisapp.R
import com.example.vitalisapp.ViewModel.DetalheRefeicaoViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class DetalheRefeicao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val idRefeicao: Int = intent.getIntExtra("ID_REFEICAO", -1)

            // Criando uma factory para criar uma view model que receba param
            val viewModel = viewModel<DetalheRefeicaoViewModel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return DetalheRefeicaoViewModel(idRefeicao) as T
                    }
                }
            )

            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetalheReceita(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DetalheReceita(
    viewModel: DetalheRefeicaoViewModel,
    modifier: Modifier = Modifier
) {
    val contexto = LocalContext.current
    val detalheReceita = viewModel.detalheRefeicaoUiState.collectAsState()

    if (detalheReceita.value.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    val listaRefeicao = Intent(contexto, ListaRefeicao::class.java)
                    contexto.startActivity(listaRefeicao)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(85.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.voltar),
                        contentDescription = "Voltar",
                        modifier = Modifier.size(26.dp)
                    )
                    Text(
                        text = "Voltar",
                        fontFamily = MavenPro,
                        color = Color.Black,
                        fontSize = 17.sp
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "${detalheReceita.value.nome}",
                    fontFamily = MavenPro,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90)
                )
                Text(
                    text = "Home > Refeição > ${detalheReceita.value.nome}",
                    fontFamily = MavenPro,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(72, 183, 90)
                )
            }
            Comida(detalheReceita.value.midias)
            InstrucoesReceita(detalheReceita.value.preparo, detalheReceita.value.alimentos)
        }
    }
}

@Composable
fun Comida(midias: MutableList<MidiaDto>?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = midias?.find { it.tipo == "Imagem" }?.caminho,
            contentDescription = "Imagem da Receita",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
    }
}

@Composable
fun InstrucoesReceita(
    preparo: String?,
    alimentos: MutableList<AlimentoPorRefeicaoDto>?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Modo de preparo:",
                    fontFamily = MavenPro,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$preparo",
                    fontSize = 16.sp,
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Ingredientes",
                    fontFamily = MavenPro,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    LazyRow(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (alimentos != null) {
                            items(items = alimentos) {
                                AlimentoRefeicao(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlimentoRefeicao(alimento: AlimentoPorRefeicaoDto?) {
    Box(
        modifier = Modifier
            .width(125.dp)
            .height(125.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = alimento?.alimento?.midia?.find { it.tipo == "Imagem" }?.caminho,
            contentDescription = "Imagem ingrediente",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = alimento?.alimento?.nome ?: "Ingrediente Undefined",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_300),
                textAlign = TextAlign.Center
            )
//            Text(
//                text = "${alimento?.metrica?.nome}",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 10.sp
//            )
            Button(
                modifier = Modifier
                    .height(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green_300),
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(1.dp),
                onClick = {
                    // Implementar Modal com mais informações sobre o alimento NA RECEITA
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "Ver mais",
                        fontSize = 9.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Refeicao() {
    VitalisAppTheme {
        DetalheReceita(DetalheRefeicaoViewModel(1))
    }
}