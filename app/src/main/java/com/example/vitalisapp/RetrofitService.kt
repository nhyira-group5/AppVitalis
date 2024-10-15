package com.example.vitalisapp

import com.example.vitalisapp.Interface.ApiExercicio
import com.example.vitalisapp.Interface.ApiRefeicao
import com.example.vitalisapp.Interface.ApiRefeicaoDiaria
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.Interface.ApiTreino
import com.example.vitalisapp.Interface.ApiUsuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    //var BASE_URL = "http://192.168.150.116:8080"
    var BASE_URL = "http://192.168.15.9:8080"

    fun getApiUsuario(): ApiUsuario {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiUsuario::class.java)

        return cliente
    }

    fun getApiTreino(): ApiTreino {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiTreino::class.java)

        return cliente
    }

    fun getApiRefeicao(): ApiRefeicao {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRefeicao::class.java)

        return cliente
    }

    fun getApiExercicio(): ApiExercicio {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiExercicio::class.java)

        return cliente
    }

    fun getApiRefeicaoDiaria(): ApiRefeicaoDiaria {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRefeicaoDiaria::class.java)

        return cliente
    }

    fun getApiRotinaDiaria(): ApiRotinaDiaria {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRotinaDiaria::class.java)

        return cliente
    }

    fun getApiRotinaSemanal(): ApiRotinaSemanal {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRotinaSemanal::class.java)

        return cliente
    }

    fun getApiRotinaMensal(): ApiRotinaMensal {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRotinaMensal::class.java)

        return cliente
    }

    fun getApiRotinaUsuario(): ApiRotinaUsuario {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRotinaUsuario::class.java)

        return cliente
    }
}