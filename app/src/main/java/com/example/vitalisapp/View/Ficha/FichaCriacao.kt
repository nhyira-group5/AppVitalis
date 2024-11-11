package com.example.vitalisapp.Entity.Ficha

data class FichaCriacao(
    private val problemaCardiaco:Int? = null,
    private val dorPeitoAtividade:Int? = null,
    private val dorPeitoUltimoMes:Int? = null,
    private val problemaOsseoArticular:Int? = null,
    private val medicamentoPressaoCoracao:Int? = null,
    private val impedimentoAtividade:Int? = null,
    private val altura: Double? = null,
    private val peso:Double? = null,
    private val usuarioId: Int? = null
)