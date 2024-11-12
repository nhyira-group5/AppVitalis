package com.example.vitalisapp

import android.app.Application
import com.example.vitalisapp.DI.modulePaymentKoin
import com.example.vitalisapp.DI.moduleRotinaDiaria
import com.example.vitalisapp.DI.moduleRotinaMensal
import com.example.vitalisapp.DI.moduleRotinaSemanal
import com.example.vitalisapp.DI.moduleRotinaUsuario
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@Application)
            modules(
                moduleRotinaUsuario,
                moduleRotinaMensal,
                moduleRotinaSemanal,
                moduleRotinaDiaria,
                modulePaymentKoin
            )
        }
    }
}