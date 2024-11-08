package com.example.vitalisapp.Entity.Ficha

import com.example.vitalisapp.View.Usuario.UsuarioGet


data class ExibitionFicha(
     val id: Int? = null,
     val problemaCardiaco:Int? = null,
     val dorPeitoAtividade:Int? = null,
     val dorPeitoUltimoMes:Int? = null,
     val problemaOsseoArticular:Int? = null,
     val medicamentoPressaoCoracao:Int? = null,
     val impedimentoAtividade:Int? = null,
     val altura: Float? = null,
     val peso:Float? = null,
     val usuarioId: UsuarioGet? = null
)