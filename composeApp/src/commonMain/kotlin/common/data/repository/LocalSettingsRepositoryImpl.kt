package common.data.repository

import common.domain.models.IsLoggedIn
import common.domain.repository.LocalSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class LocalSettingsRepositoryImpl: LocalSettingsRepository {
    override var accessToken: String = ""
    override var refreshToken: String = ""

    val initialLoggedInState = IsLoggedIn(
        userID = 0,
        username = "",
        role = "",
        state = false
    )

    var _isLoggedInState = MutableStateFlow<IsLoggedIn>(initialLoggedInState)

    override fun isLoggedIn() : StateFlow<IsLoggedIn> {
        return _isLoggedInState.asStateFlow()
    }

   override fun setIsLoggedInState(isLoggedIn: IsLoggedIn){
        _isLoggedInState.value = isLoggedIn
    }

}