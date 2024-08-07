package common.domain.models

import kotlinx.coroutines.flow.MutableStateFlow

class IsLoggedIn (
    var state: Boolean,
    var username: String,
    var userID: Int,
    var role: String
    )