package referal.presentation.events

sealed class ReferralUIEvent {
    data object showMenu: ReferralUIEvent()
    data object showNewReferral: ReferralUIEvent()
    data object showPending: ReferralUIEvent()
    data object showAll: ReferralUIEvent()

}