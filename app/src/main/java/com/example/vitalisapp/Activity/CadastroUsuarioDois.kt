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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalisapp.R
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
    val metaOptions = listOf("Emagracimento", "Ganho de Massa", "Flexibilidade")
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
                .padding(8.dp)
        ) {
            Column {
                OutlinedTextField(
                    value = meta,
                    onValueChange = {meta = it},
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
                    metaOptions.forEach { metaOption ->
                        DropdownMenuItem(
                            text = { Text(text = metaOption) },
                            onClick = {
                                meta = metaOption
                                metaExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = problemaCoracao,
                onCheckedChange = { problemaCoracao = it },
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
                checked = dorPeito,
                onCheckedChange = { dorPeito = it },
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
                checked = tontura,
                onCheckedChange = { tontura = it },
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
                checked = problemaOsseo,
                onCheckedChange = { problemaOsseo = it },
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
                checked = medicamento,
                onCheckedChange = { medicamento = it },
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

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun GreetingPreview2() {
    VitalisAppTheme {
        SegundaParte("Android")
    }
}