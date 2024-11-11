package com.example.vitalisapp.DTO.Endereco

// Eles retornam tudo em String
data class ViaCepDto(
    var cep: String? = null,
    var lougradouro: String? = null,
    var complemento: String? = null,
    var unidade: String? = null,
    var bairro: String? = null,
    var localidade: String? = null,
    var uf: String? = null,
    var estado: String? = null,
    var regiao: String? = null,
    var ibge: String? = null,
    var gia: String? = null,
    var ddd: String? = null,
    var siafi: String? = null
) {  }