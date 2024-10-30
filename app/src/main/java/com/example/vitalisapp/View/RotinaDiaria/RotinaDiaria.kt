package com.example.vitalisapp.View.RotinaDiaria

import com.example.vitalisapp.View.RotinaSemanal.RotinaSemanal

data class RotinaDiaria (
    var idRotinaDiaria: Int,
    var rotinaSemanalId: RotinaSemanal,
    var dia: Int,
    var concluido: Int
)