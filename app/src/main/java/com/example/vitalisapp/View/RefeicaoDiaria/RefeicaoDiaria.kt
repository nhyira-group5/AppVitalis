package com.example.vitalisapp.View.RefeicaoDiaria

import com.example.vitalisapp.View.Refeicao.Refeicao
import com.example.vitalisapp.View.RotinaDiaria.RotinaDiaria
import com.google.gson.annotations.SerializedName

data class RefeicaoDiaria (
    var idRefeicaoDiaria: Int,
    @SerializedName("refeicaoId") var refeicao: Refeicao,
    @SerializedName("rotinaDiariaId") var rotinaDiaria: RotinaDiaria,
    var concluido: Int
)