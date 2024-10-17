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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.ui.theme.MavenPro
import com.example.vitalisapp.ui.theme.VitalisAppTheme

class CadastroUsuarioDois : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalisAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SegundaParte(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SegundaParte(name: String, modifier: Modifier = Modifier) {
    var peso by remember { mutableStateOf(TextFieldValue("")) }
    var altura by remember { mutableStateOf(TextFieldValue("")) }
    var meta by remember { mutableStateOf("") }
    var metaExpanded by remember { mutableStateOf(false) }
    val metaOptions = listOf("Perder peso", "Ganho de Massa", "Flexibilidade")
    var problemaCoracao by remember { mutableStateOf(false) }
    var dorPeito by remember { mutableStateOf(false) }
    var tontura by remember { mutableStateOf(false) }
    var problemaOsseo by remember { mutableStateOf(false) }
    var medicamento by remember { mutableStateOf(false) }
    val customColor = Color(72, 183, 90)
    val scrollState = rememberScrollState()

    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Estamos quase lá!",
            fontFamily = MavenPro,
            color = customColor,
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 20.dp, top=16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Antes de você acessar nosso sistema, você deve informar algumas informações sobre você e sua saúde.",
            fontFamily = MavenPro,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            InputText(
                value = "",
                onValueChange = {},
                label = "Peso",
                modifier = Modifier.weight(1f)
            )

            InputText(
                value = "",
                onValueChange = { },
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
            OutlinedTextField(
                value = meta,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Meta", color = Color.White) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }

        MetaDropdownMenu(
            metaOptions = metaOptions,
            metaExpanded = metaExpanded,
            onMetaChange = { selectedMeta ->
                meta = selectedMeta
                metaExpanded = false
            },
            onDismissRequest = { metaExpanded = false }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = dorPeito,
                onCheckedChange = { dorPeito = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "Algum médico já disse que você possui algum problema de coração e que só deveria realizar atividade física supervisionado por profissionais de saúde?",
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = dorPeito,
                onCheckedChange = { dorPeito = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "Você sente dores no peito quando pratica atividade física?",
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = tontura,
                onCheckedChange = { tontura = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "No último mês, você sentiu dores no peito quando praticou atividade física?",
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = problemaOsseo,
                onCheckedChange = { problemaOsseo = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "Você apresenta desequilíbrio devido à tontura e/ou perda de consciência?",
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = medicamento,
                onCheckedChange = { medicamento = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "Você possui algum problema ósseo ou articular que poderia ser piorado pela atividade física?",
                fontFamily = MavenPro,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = dorPeito,
                onCheckedChange = { dorPeito = it },
                colors = CheckboxDefaults.colors(checkedColor = customColor)
            )
            Text(
                text = "Você toma atualmente algum medicamento para pressão arterial e/ou problema de coração?",
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
                    val inicio = Intent(contexto, Inicio::class.java)
                    contexto.startActivity(inicio)
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

@Composable
fun MetaDropdownMenu(
    metaOptions: List<String>,
    metaExpanded: Boolean,
    onMetaChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = metaExpanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        metaOptions.forEach { option ->
            // Usando um Box para criar um item de menu personalizável
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMetaChange(option)
                        onDismissRequest() // Fecha o menu após a seleção
                    }
                    .padding(8.dp)
            ) {
                Text(
                    text = option,
                    color = Color.Black
                )
            }
        }
    }
}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun GreetingPreview2() {
    VitalisAppTheme {
        SegundaParte("Android")
    }
}