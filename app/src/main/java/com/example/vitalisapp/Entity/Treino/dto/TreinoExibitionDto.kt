package com.example.vitalisapp.Entity.Treino.dto

import com.example.vitalisapp.Entity.Exercicio.dto.ExercicioExibitionDto
import com.example.vitalisapp.Entity.RotinaDiaria.dto.RotinaDiariaExibitionDto
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionDto (
    val idTreino: Int,
    @SerializedName("exercicioId") val exercicio: ExercicioExibitionDto,            // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    @SerializedName("rotinaDiariaId") val rotinaDiaria: RotinaDiariaExibitionDto,   // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    val serie: Int,
    val repeticao: Int,
    val tempo: LocalTime,
    val concluido: Int
)