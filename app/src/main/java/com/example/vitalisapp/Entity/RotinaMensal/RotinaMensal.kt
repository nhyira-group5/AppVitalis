package com.example.vitalisapp.Entity.RotinaMensal

import com.example.vitalisapp.Entity.RotinaUsuario.RotinaUsuario

data class RotinaMensal (
    var idRotinaMensal: Int,
    var rotinaUsuarioId: RotinaUsuario,
    var mes: Int,
    var ano: Int,
    var concluido: Int
)