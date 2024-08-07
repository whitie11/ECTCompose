package treatment.presentation

import androidx.lifecycle.ViewModel
import common.domain.repository.LocalSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import treatment.presentation.stateObjs.TreatmentUIState

class TreatmentViewModel(
    private val localSettingsRepository: LocalSettingsRepository
) : ViewModel() {
    private val _treatmentUIState = MutableStateFlow<TreatmentUIState>(TreatmentUIState.TreatmentHome)
    val treatmentUIState = _treatmentUIState.asStateFlow()

    var isLoggedInState = localSettingsRepository.isLoggedIn()
    val screenTitle = "ECT Pathway: Treatment"


//    fun onReferralEvent(event: ReferralUIEvent) {
//        when (event) {
//            ReferralUIEvent.showAll -> {
//                _referralUIState.value = ReferralUIState.ListAll
//            }
//
//            ReferralUIEvent.showMenu -> {
//                _referralUIState.value = ReferralUIState.Menu
//            }
//
//            ReferralUIEvent.showNewReferral -> {
//                _referralUIState.value = ReferralUIState.NewReferral
//            }
//
//            ReferralUIEvent.showPending -> {
//                _referralUIState.value = ReferralUIState.ListPending
//            }
//        }
//    }


}