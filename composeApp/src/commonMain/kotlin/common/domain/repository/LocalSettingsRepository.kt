package common.domain.repository

import common.domain.models.IsLoggedIn
import kotlinx.coroutines.flow.StateFlow

interface LocalSettingsRepository {
    var accessToken: String
    var refreshToken: String

    fun  isLoggedIn() : StateFlow<IsLoggedIn>

    fun setIsLoggedInState(isLoggedIn: IsLoggedIn)
}