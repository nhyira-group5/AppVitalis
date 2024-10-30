package com.example.vitalisapp.View.Midia

import com.example.vitalisapp.View.Alimento.Alimento
import com.example.vitalisapp.View.Exercicio.Exercicio
import com.example.vitalisapp.View.Refeicao.Refeicao

data class Midia (
    var idMidia: Int,
    var alimentoId: Alimento,
    var exercicioId: Exercicio,
    var refeicaoId: Refeicao,
    var nome: String,
    var caminho: String,
    var extensao: String,
    var tipo: String
)