package com.example.vitalisapp.DTO.Pagamento

import com.example.vitalisapp.DTO.Usuario.UsuarioExibitionDto
import com.google.gson.annotations.SerializedName

data class PagamentoCreateEditDto (
    var usuarioId: Int,
    var tipo: String,
    @SerializedName("assinaturaId") var assinatura: Int
) {}