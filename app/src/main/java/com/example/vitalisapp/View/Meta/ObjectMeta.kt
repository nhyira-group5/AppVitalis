package com.example.vitalisapp.View.Meta

object ObjectMeta {
    var usuarioId : Int? = null
    var metaId : Int? = null
    var rotinaAlternativa: Int? = null

    fun inicializarSaude(
        usuarioId : Int? = null,
        metaId : Int? = null,
        rotinaAlternativa: Int? = null
    ) {
        this.usuarioId = usuarioId
        this.metaId = metaId
        this.rotinaAlternativa = rotinaAlternativa
    }
}