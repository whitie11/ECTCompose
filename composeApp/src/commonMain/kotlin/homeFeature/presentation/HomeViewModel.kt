package homeFeature.presentation

import androidx.lifecycle.ViewModel
import common.domain.repository.LocalSettingsRepository

class HomeViewModel(
    private val localSettingsRepository: LocalSettingsRepository
) : ViewModel() {
    var isLoggedInState = localSettingsRepository.isLoggedIn()
    val screenTitle = "ECT Pathway!"
}