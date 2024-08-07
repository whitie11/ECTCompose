package net.whiteapps.ectmanager.data.dto



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import auth.domain.models.TokenResponse

@Serializable
data class TokenResponseDTO(
    @SerialName("access")
    var access: String,
    @SerialName("refresh")
    var refresh: String
)

fun TokenResponseDTO.toTokenResponse(): TokenResponse {
    return TokenResponse(
        access = access,
        refresh = refresh
    )
}

