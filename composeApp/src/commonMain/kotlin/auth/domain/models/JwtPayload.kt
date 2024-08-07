package auth.domain.models



import ectpathway.composeapp.generated.resources.Res
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.*


@Serializable
data class JwtPayload (
    @SerialName("token_type")
    val tokenType: String? = null,
    @SerialName("exp")
    val exp: Long? = null,
    @SerialName("iat")
    val iat: Long? = null,
    @SerialName("jti")
    val jti: String? = null,
    @SerialName("user_id")
    val userID: Int? = null
)
