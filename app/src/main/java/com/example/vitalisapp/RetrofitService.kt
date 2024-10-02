package com.example.vitalisapp

import com.example.vitalisapp.Interface.ApiUsuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {


    var BASE_URL = "http://192.168.150.116:8080"

    fun getApiUsuario(): ApiUsuario {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiUsuario::class.java)

        return cliente
    }


}