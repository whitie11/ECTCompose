package referal.presentation

import androidx.lifecycle.ViewModel
import auth.presentation.events.LoginUIEvent
import auth.presentation.stateObjs.LogInState
import common.domain.repository.LocalSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import referal.presentation.events.ReferralUIEvent
import referal.presentation.stateObjs.ReferralUIState

class ReferralViewModel(
    private val localSettingsRepository: LocalSettingsRepository
) : ViewModel() {
    private val _referralUIState = MutableStateFlow<ReferralUIState>(ReferralUIState.Menu)
    val referralUIState = _referralUIState.asStateFlow()

    var isLoggedInState = localSettingsRepository.isLoggedIn()
    val screenTitle = "ECT Pathway: Referrals"


    fun onReferralEvent(event: ReferralUIEvent) {
        when (event) {
            ReferralUIEvent.showAll -> {
                _referralUIState.value = ReferralUIState.ListAll
            }
            ReferralUIEvent.showMenu -> {
                _referralUIState.value = ReferralUIState.Menu
            }
            ReferralUIEvent.showNewReferral -> {
                _referralUIState.value = ReferralUIState.NewReferral
            }
            ReferralUIEvent.showPending -> {
                _referralUIState.value = ReferralUIState.ListPending
            }
        }
        }
}