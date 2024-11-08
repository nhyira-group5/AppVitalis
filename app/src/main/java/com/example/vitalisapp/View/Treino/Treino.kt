package com.example.vitalisapp.View.Treino

import com.example.vitalisapp.View.Exercicio.Exercicio
import com.example.vitalisapp.View.RotinaDiaria.RotinaDiaria
import java.time.LocalTime

data class Treino (
    var idTreino: Int,
    var exercicioId: Exercicio,
    var rotinaDiariaId: RotinaDiaria,
    var serie: Int,
    var repeticao: Int,
    var tempo: LocalTime,
    var concluido: Int
)