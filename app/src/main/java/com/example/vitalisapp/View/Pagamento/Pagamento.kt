package com.example.vitalisapp.View.Pagamento

import com.example.vitalisapp.View.Assinatura.Assinatura
import com.example.vitalisapp.View.Usuario.Usuario
import java.time.LocalDate

class Pagamento(
    var idPagamento: Int,
    var usuarioId: Usuario,
    var assinaturaId: Assinatura,
    var dataPagamento: LocalDate,
    var tipo: String
    ) {}