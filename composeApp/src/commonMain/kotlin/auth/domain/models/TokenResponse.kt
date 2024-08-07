package auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val access: String = "",
    val refresh: String = ""
    )
