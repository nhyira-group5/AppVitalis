package com.example.vitalisapp.Entity.Treino.dto

import com.example.vitalisapp.Entity.Exercicio.dto.ExercicioExibitionDto
import com.example.vitalisapp.Entity.RotinaDiaria.dto.RotinaDiariaExibitionDto
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionDto (
    var idTreino: Int,
    @SerializedName("exercicioId") var exercicio: ExercicioExibitionDto,            // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    @SerializedName("rotinaDiariaId") var rotinaDiaria: RotinaDiariaExibitionDto,   // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    var serie: Int,
    var repeticao: Int,
    var tempo: LocalTime,
    var concluido: Boolean
)