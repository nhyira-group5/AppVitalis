package com.example.vitalisapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitalisapp.Entity.Ficha.FichaCriacao
import com.example.vitalisapp.R
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioCreateEditDto
import com.example.vitalisapp.Service.MetaViewModel
import com.example.vitalisapp.Service.RotinaUsuarioModel
import com.example.vitalisapp.ViewModel.FichaViewModel

class CadastroUsuarioDois : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SegundaParte(
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
fun SegundaParte(name: String, navController: NavHostController, modifier: Modifier = Modifier, viewModel: FichaViewModel = viewModel(), viewModelMeta: MetaViewModel = viewModel(), viewModelRotUsuario: RotinaUsuarioModel = viewModel() ) {


//    val rotinaUsuario = homeUiState.rotinaUsuario

    var peso by remember { mutableStateOf(0.0) }
    var altura by remember { mutableStateOf(0.0) }
    var meta by remember { mutableStateOf("") }
    var idMeta by remember {
        mutableStateOf(0)
    }
    var metaExpanded by remember { mutableStateOf(false) }
    val metaOptions = listOf("Emagracimento", "Ganho de Massa", "Flexibilidade")
    var problemasCardiacos by remember { mutableStateOf(0) }
    var dorPeito by remember { mutableStateOf(0) }
    var dorPeitoUltimoMes by remember { mutableStateOf(0) }
    var problemaOsseo by remember { mutableStateOf(0) }
    var medicamentoPressaoCoracao by remember { mutableStateOf(0) }
    var impedimentoAtividade by remember { mutableStateOf(0) }


    val customColor = Color(72, 183, 90)
    val scrollState = rememberScrollState()

    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        viewModelMeta.getLista()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.quase),
            fontFamily = MavenPro,
            color = customColor,
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 20.dp, top=16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text =stringResource(R.string.sub_cadast),
            fontFamily = MavenPro,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            InputText(
                value = peso.toString(), // Converter o valor Double para String
                onValueChange = { novoValor ->
                    // Tenta converter o novo valor para Double e atualiza a variável 'peso'
                    peso = novoValor.toDoubleOrNull() ?: 0.0
                },
                label ="Peso",
                modifier = Modifier.weight(1f)
            )

            InputText(
                value = altura.toString(),
                onValueChange = { novoValor ->
                    // Tenta converter o novo valor para Double e atualiza a variável 'peso'
                    altura = novoValor.toDoubleOrNull() ?: 0.0 },
                label = "Altura (cm)",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { metaExpanded = true }
                .padding(8.dp)
        ) {
            Column {
                OutlinedTextField(
                    value = meta,
                    onValueChange = { meta = it },
                    readOnly = true,
                    label = { Text(text = "Meta") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.clickable { metaExpanded = !metaExpanded }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                DropdownMenu(
                    expanded = metaExpanded,
                    onDismissRequest = { metaExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModelMeta.listaMetas.value.forEach { item ->
                        DropdownMenuItem(text = {
                            Text(text = item.nome!!)
                        },
                            onClick = {
                                meta = item.nome!!
                                metaExpanded = false
                                idMeta = item.id!!
                            })
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = problemasCardiacos == 1,
                onCheckedChange = { problemasCardiacos = if (it) 1 else 0 },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = stringResource(R.string.dor_core),
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = dorPeito == 1,
                onCheckedChange = { problemasCardiacos = if (it) 1 else 0  },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = stringResource(R.string.dor_peito),
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = dorPeitoUltimoMes == 1,
                onCheckedChange = { dorPeitoUltimoMes = if(it) 1 else 0 },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = stringResource(R.string.tontura),
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = problemaOsseo == 1,
                onCheckedChange = { problemaOsseo = if(it) 1 else 0},
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = stringResource(R.string.osseo),
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = medicamentoPressaoCoracao == 1,
                onCheckedChange = { medicamentoPressaoCoracao = if(it) 1 else 0 },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = stringResource(R.string.pressao),
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val fichaCriacao = FichaCriacao(problemasCardiacos, dorPeito, dorPeitoUltimoMes, problemaOsseo, medicamentoPressaoCoracao, impedimentoAtividade, altura, peso, 10)
                    viewModel.createFicha(fichaCriacao)
                    val confirmacaoCadastro = Intent(contexto, ConfirmacaoParq::class.java)
                    val rotinaUsuario = RotinaUsuarioCreateEditDto(10, idMeta)
                    viewModelRotUsuario.createRotinaUsuario(rotinaUsuario)
//                    viewModel.rotinaUsuarioModel.createRotinaUsuario(rotinaUsuario)
                    contexto.startActivity(confirmacaoCadastro)
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = customColor
                )
            ) {
                Text(
                    text = "Prosseguir",
                    fontFamily = MavenPro,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun GreetingPreview2() {
    VitalisAppTheme {
        SegundaParte("Android", rememberNavController())
    }
}