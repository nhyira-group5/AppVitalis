package com.example.vitalisapp.DI

import com.example.vitalisapp.DTO.Pagamento.PaymentKoinDto
import com.example.vitalisapp.DTO.RotinaDiaria.RotinaDiariaExibitionDto
import com.example.vitalisapp.DTO.RotinaMensal.RotinaMensalExibitionDto
import com.example.vitalisapp.DTO.RotinaSemanal.RotinaSemanalExibitionDto
import com.example.vitalisapp.DTO.RotinaUsuario.RotinaUsuarioExibitionDto
import com.example.vitalisapp.Interface.ApiRotinaDiaria
import com.example.vitalisapp.Interface.ApiRotinaMensal
import com.example.vitalisapp.Interface.ApiRotinaSemanal
import com.example.vitalisapp.Interface.ApiRotinaUsuario
import com.example.vitalisapp.RetrofitService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleRotinaUsuario = module {
    single<RotinaUsuarioExibitionDto> {
        RotinaUsuarioExibitionDto()
    }

    single<ApiRotinaUsuario> {
        RetrofitService.getApiRotinaUsuario()
    }
}

val moduleRotinaMensal = module {
    single<RotinaMensalExibitionDto> {
        RotinaMensalExibitionDto()
    }

    single<ApiRotinaMensal> {
        RetrofitService.getApiRotinaMensal()
    }
}

val moduleRotinaSemanal = module {
    single<RotinaSemanalExibitionDto> {
        RotinaSemanalExibitionDto()
    }

    single<ApiRotinaSemanal> {
        RetrofitService.getApiRotinaSemanal()
    }
}

val moduleRotinaDiaria = module {
    single<RotinaDiariaExibitionDto> {
        RotinaDiariaExibitionDto()
    }

    single<ApiRotinaDiaria> {
        RetrofitService.getApiRotinaDiaria()
    }
}

val modulePaymentKoin = module {
    scope(named("paymentSession")) {
        scoped { PaymentKoinDto() }
    }
}
