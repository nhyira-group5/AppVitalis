package com.example.vitalisapp.Exceptions

class ApiException (
    val title: String,
    val error: String?
) : Exception("Erro na API - ${title}: ${error}")