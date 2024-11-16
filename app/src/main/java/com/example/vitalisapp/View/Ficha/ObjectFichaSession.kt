package com.example.vitalisapp.View.Ficha

object ObjectFichaSession {
    var problemaCardiaco:Int? = null
    var dorPeitoAtividade:Int? = null
    var dorPeitoUltimoMes:Int? = null
    var problemaOsseoArticular:Int? = null
    var medicamentoPressaoCoracao:Int? = null
    var altura: Double? = null
    var impedimentoAtividade:Int? = null
    var peso:Double? = null
    var usuarioId: Int? = null

    fun inicializarSaude(
        problemaCardiaco: Int? = null,
        dorPeitoAtividade: Int? = null,
        dorPeitoUltimoMes: Int? = null,
        problemaOsseoArticular: Int? = null,
        medicamentoPressaoCoracao: Int? = null,
        altura: Double? = null,
        impedimentoAtividade: Int? = null,
        peso: Double? = null,
        usuarioId: Int? = null
    ) {
        this.problemaCardiaco = problemaCardiaco
        this.dorPeitoAtividade = dorPeitoAtividade
        this.dorPeitoUltimoMes = dorPeitoUltimoMes
        this.problemaOsseoArticular = problemaOsseoArticular
        this.medicamentoPressaoCoracao = medicamentoPressaoCoracao
        this.altura = altura
        this.impedimentoAtividade = impedimentoAtividade
        this.peso = peso
        this.usuarioId = usuarioId
    }

}