package com.example.vitalisapp

import com.example.vitalisapp.Interface.ApiEndereco
import com.example.vitalisapp.Interface.ApiExercicio
import com.example.vitalisapp.Interface.externals.ApiViaCep
import com.example.vitalisapp.Interface.ApiPagamento
import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRefeicaoDiaria
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.Interface.ApiTreino
import com.example.vitalisapp.Interface.ApiUsuario

data class GlobalUiState(
    val apiViaCep: ApiViaCep = RetrofitService.getApiViaCep(),
    val apiUsuario: ApiUsuario = RetrofitService.getApiUsuario(),
    val apiRotinaUsuario: ApiRotinaUsuario = RetrofitService.getApiRotinaUsuario(),
    val apiRotinaMensal: ApiRotinaMensal = RetrofitService.getApiRotinaMensal(),
    val apiRotinaSemanal: ApiRotinaSemanal = RetrofitService.getApiRotinaSemanal(),
    val apiRotinaDiaria: ApiRotinaDiaria = RetrofitService.getApiRotinaDiaria(),
    val apiRefeicao: ApiRefeicao = RetrofitService.getApiRefeicao(),
    val apiTreino: ApiTreino = RetrofitService.getApiTreino(),
    val apiExercicio: ApiExercicio = RetrofitService.getApiExercicio(),
    val apiRefeicaoDiaria: ApiRefeicaoDiaria = RetrofitService.getApiRefeicaoDiaria(),
    val apiPagamento: ApiPagamento = RetrofitService.getApiPagamento(),
    val apiEndereco: ApiEndereco = RetrofitService.getApiEndereco(),
) {}