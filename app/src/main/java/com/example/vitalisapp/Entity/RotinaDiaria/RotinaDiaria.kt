package com.example.vitalisapp.Entity.RotinaDiaria

import com.example.vitalisapp.Entity.RotinaSemanal.RotinaSemanal

data class RotinaDiaria (
    var idRotinaDiaria: Int,
    var rotinaSemanalId: RotinaSemanal,
    var dia: Int,
    var concluido: Int
)