package com.example.vitalisapp.Entity.Midia

import com.example.vitalisapp.Entity.Alimento.Alimento
import com.example.vitalisapp.Entity.Exercicio.Exercicio
import com.example.vitalisapp.Entity.Refeicao.Refeicao

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