package referal.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import referal.presentation.ReferralViewModel

val ReferralViewModelModule = module {
    viewModelOf(::ReferralViewModel)
}