package treatment.presentation.stateObjs

sealed class TreatmentUIState {
    data object TreatmentHome : TreatmentUIState()
    data object PreChecks : TreatmentUIState()
}

