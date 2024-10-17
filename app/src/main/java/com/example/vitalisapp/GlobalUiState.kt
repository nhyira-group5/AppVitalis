package com.example.vitalisapp

import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRefeicaoDiaria
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.Interface.ApiTreino

data class GlobalUiState(
    val apiRotinaUsuario: ApiRotinaUsuario = RetrofitService.getApiRotinaUsuario(),
    val apiRotinaMensal: ApiRotinaMensal = RetrofitService.getApiRotinaMensal(),
    val apiRotinaSemanal: ApiRotinaSemanal = RetrofitService.getApiRotinaSemanal(),
    val apiRotinaDiaria: ApiRotinaDiaria = RetrofitService.getApiRotinaDiaria(),
    val apiRefeicao: ApiRefeicao = RetrofitService.getApiRefeicao(),
    val apiTreino: ApiTreino = RetrofitService.getApiTreino(),
    val apiRefeicaoDiaria: ApiRefeicaoDiaria = RetrofitService.getApiRefeicaoDiaria()
) {}