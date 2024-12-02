package com.example.vitalisapp.Activity

import android.app.Activity
import android.content.Intent
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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitalisapp.DTO.Midia.MidiaDto
import com.example.vitalisapp.R
import com.example.vitalisapp.ViewModel.DetalheExercicioViewModel
import com.example.vitalisapp.ViewModel.DetalheRefeicaoViewModel
import com.example.vitalisapp.ViewModel.ListaExercicioViewModel
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import androidx.compose.ui.viewinterop.AndroidView
import com.example.vitalisapp.DTO.Exercicio.TagDto

class DetalheExercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val idExercicio: Int = intent.getIntExtra("ID_EXERCICIO", -1)


            val viewModel = viewModel<DetalheExercicioViewModel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return DetalheExercicioViewModel(idExercicio) as T
                    }
                }
            )

            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetalheExercicio(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DetalheExercicio(
    viewModel: DetalheExercicioViewModel,
    modifier: Modifier = Modifier
) {
    val contexto = LocalContext.current
    val detalheExercicio = viewModel.detalheExercicioUiState.collectAsState()

    if (detalheExercicio.value.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Button(
                onClick = {
                    (contexto as? Activity)?.finish()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.voltar),
                    contentDescription = "Voltar"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Voltar",
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${detalheExercicio.value.exercicio?.nome}",
                fontFamily = MavenPro,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90)
            )


            Spacer(modifier = Modifier.height(5.dp))


            Text(
                text = "Home > Treinos > ${detalheExercicio.value.exercicio?.nome}",
                fontFamily = MavenPro,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(72, 183, 90)
            )

            Spacer(modifier = Modifier.height(10.dp))


            VideoExercicio(detalheExercicio.value.exercicio?.midias)

            Execucao(
                serie = detalheExercicio.value.serie,
                repeticao = detalheExercicio.value.repeticao,
                tempo = detalheExercicio.value.tempo
            )


            Informacoes(
                descricao = detalheExercicio.value.exercicio?.descricao,
                tags = detalheExercicio.value.exercicio?.tagExercicioDtos
                    ?.map { it.tag?.nome }
                    ?.filterNotNull()
            )



            BotaoConcluido(
                idTreino = detalheExercicio.value.id ?: 0,
                concluido = detalheExercicio.value.concluido ?: 0
            )



        }
    }
}




@Composable
fun VideoExercicio(midias: MutableList<MidiaDto>?) {
    val videoUrl = midias?.find { it.tipo == "Video" }?.caminho
    val videoId = extractYouTubeId(videoUrl)

    if (videoId != null) {
        AndroidView(
            factory = { context ->
                YouTubePlayerView(context).apply {
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {

                            youTubePlayer.cueVideo(videoId, 0f)
                        }
                    })
                }
            },
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f)
        )
    } else {
        Text("Vídeo não disponível", modifier = Modifier.padding(16.dp))
    }
}

fun extractYouTubeId(url: String?): String? {
    url?.let {
        val regex = Regex("(?:youtu\\.be/|youtube\\.com/(?:.*v(?:/|=)|(?:.*\\/)?)([a-zA-Z0-9-_]{11}))")
        return regex.find(it)?.groupValues?.get(1)
    }
    return null
}

@Composable
fun Informacoes(descricao: String?, tags: List<String>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Descrição",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = descricao ?: "Descrição não disponível",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        tags?.let {
            TagsLayout(
                tags = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun TagsLayout(tags: List<String>, modifier: Modifier = Modifier) {
    var currentRowWidth = 0
    val maxRowWidth = 500

    Column(modifier = modifier) {
        var rowTags = mutableListOf<String>()

        tags.forEach { tag ->
            val tagWidth = tag.length * 14

            if (currentRowWidth + tagWidth > maxRowWidth) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    rowTags.forEach {
                        Tag(text = it)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                rowTags.clear()
                currentRowWidth = 0
            }

            rowTags.add(tag)
            currentRowWidth += tagWidth + 16
        }

        if (rowTags.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                rowTags.forEach {
                    Tag(text = it)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
fun Execucao(
    serie: Int?,
    repeticao: Int?,
    tempo: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = stringResource(R.string.informação),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    InfoCard(title = "Tempo de execução", value = tempo ?: "N/A")
                    InfoCard(title = "Número de repetições", value = "${repeticao ?: 0} repetições")
                    InfoCard(title = "Número de séries", value = "${serie ?: 0} séries de repetições")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview14() {
    VitalisAppTheme {
        viewModel<DetalheExercicioViewModel>()
    }
}