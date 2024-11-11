package com.example.vitalisapp

import com.example.vitalisapp.Interface.ApiEndereco
import com.example.vitalisapp.Interface.ApiExercicio
import com.example.vitalisapp.Interface.ApiFicha
import com.example.vitalisapp.Interface.ApiMeta
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

    var BASE_URL = "http://192.168.0.11:8080"
//    var BASE_URL = "http://192.168.145.116:8080"
//    var BASE_URL = "http://18.211.206.217:5225"

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


    fun getApiFicha(): ApiFicha {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiFicha::class.java)

        return cliente
    }

    fun getApiMeta(): ApiMeta {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiMeta::class.java)

        return cliente
    }



    fun getApiEndereco(): ApiEndereco {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndereco::class.java)

        return cliente
    }

//    var BASE_VIA_CEP = "https://viacep.com.br/ws/"
//
//
//    fun getAPiViaCep(): ApiEndereco{
//        val cliente =
//            Retrofit.Builder()
//                .baseUrl(BASE_VIA_CEP)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiEndereco::class.java)
//
//        return cliente
//    }

}