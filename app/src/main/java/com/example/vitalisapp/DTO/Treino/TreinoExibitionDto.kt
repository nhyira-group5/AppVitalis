package com.example.vitalisapp.DTO.Treino

import com.example.vitalisapp.DTO.Exercicio.ExercicioExibitionDto
import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TreinoExibitionDto (
    val idTreino: Int,
    @SerializedName("exercicioId") val exercicio: ExercicioExibitionDto,            // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    @SerializedName("rotinaDiariaId") val rotinaDiaria: RotinaDiariaExibitionDto,   // Preguiça de fazer uma DTO e o Exibition já faz oq quero kkkk
    val serie: Int,
    val repeticao: Int,
    val tempo: String,
    val concluido: Int
)