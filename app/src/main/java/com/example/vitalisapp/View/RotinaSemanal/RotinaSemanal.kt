package com.example.vitalisapp.View.RotinaSemanal

import com.example.vitalisapp.View.RotinaMensal.RotinaMensal

data class RotinaSemanal (
    var idRotinaSemanal: Int,
    var rotinaMensalId: RotinaMensal,
    var numSemana: Int,
    var concluido: Int
)