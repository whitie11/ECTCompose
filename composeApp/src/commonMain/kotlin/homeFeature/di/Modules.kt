package homeFeature.di

import homeFeature.presentation.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val HomeViewModelModule = module {
    viewModelOf(::HomeViewModel)
}