package com.example.vitalisapp.Entity.RotinaSemanal

import com.example.vitalisapp.Entity.RotinaMensal.RotinaMensal

data class RotinaSemanal (
    var idRotinaSemanal: Int,
    var rotinaMensalId: RotinaMensal,
    var numSemana: Int,
    var concluido: Int
)