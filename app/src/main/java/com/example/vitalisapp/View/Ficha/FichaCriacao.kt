package com.example.vitalisapp.Entity.Ficha

data class FichaCriacao(
    private val problemaCardiaco:Int,
    private val dorPeitoAtividade:Int,
    private val dorPeitoUltimoMes:Int,
    private val problemaOsseoArticular:Int,
    private val medicamentoPressaoCoracao:Int,
    private val impedimentoAtividade:Int,
    private val altura: Double,
    private val peso:Double,
    private val usuarioId: Int? = null
)