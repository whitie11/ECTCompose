package treatment.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import treatment.presentation.TreatmentViewModel

val TreatmentViewModelModule = module {
    viewModelOf(::TreatmentViewModel)
}