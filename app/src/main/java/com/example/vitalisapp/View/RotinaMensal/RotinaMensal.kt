package com.example.vitalisapp.View.RotinaMensal

import com.example.vitalisapp.View.RotinaUsuario.RotinaUsuario

data class RotinaMensal (
    var idRotinaMensal: Int,
    var rotinaUsuarioId: RotinaUsuario,
    var mes: Int,
    var ano: Int,
    var concluido: Int
)