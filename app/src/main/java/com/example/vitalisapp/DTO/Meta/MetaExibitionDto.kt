package com.example.vitalisapp.DTO.Meta

import com.google.gson.annotations.SerializedName

data class MetaExibitionDto (
    @SerializedName("id") var idMeta: Int? = null,
    var nome: String? = null
)