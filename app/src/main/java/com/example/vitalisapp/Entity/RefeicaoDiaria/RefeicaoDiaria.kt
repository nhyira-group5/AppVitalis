package com.example.vitalisapp.Entity.RefeicaoDiaria

import com.example.vitalisapp.Entity.Refeicao.Refeicao
import com.example.vitalisapp.Entity.RotinaDiaria.RotinaDiaria
import com.google.gson.annotations.SerializedName

data class RefeicaoDiaria (
    var idRefeicaoDiaria: Int,
    @SerializedName("refeicaoId") var refeicao: Refeicao,
    @SerializedName("rotinaDiariaId") var rotinaDiaria: RotinaDiaria,
    var concluido: Int
)