package di

import auth.di.AuthApiModule
import auth.di.AuthRepositoryModule
import auth.di.AuthViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import homeFeature.di.HomeViewModelModule
import referal.di.ReferralViewModelModule
import treatment.di.TreatmentViewModelModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            AuthApiModule,
            AuthRepositoryModule,
            AuthViewModelModule,
            provideLocalSettingsRepository,
            HomeViewModelModule,
            ReferralViewModelModule,
            TreatmentViewModelModule
        )
    }
}