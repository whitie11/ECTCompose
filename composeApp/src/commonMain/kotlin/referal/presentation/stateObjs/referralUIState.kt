package referal.presentation.stateObjs

sealed class ReferralUIState {
    data object Menu : ReferralUIState()
    data object NewReferral : ReferralUIState()
    data object ListPending : ReferralUIState()
    data object ListAll : ReferralUIState()

}