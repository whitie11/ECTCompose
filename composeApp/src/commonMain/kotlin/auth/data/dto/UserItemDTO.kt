package auth.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import auth.domain.models.UserItem

@Serializable
data class UserItemDTO(
    @SerialName("email")
    var email: String,
    @SerialName("first_name")
    var firstName: String = "",
    @SerialName("id")
    var id: Int,
    @SerialName("is_active")
    var isActive: Boolean = false,
    @SerialName("last_name")
    var lastName: String = "",
    @SerialName("role")
    var role: String,
    @SerialName("username")
    var username: String
)

fun UserItemDTO.toUserItem(): UserItem {
    return UserItem(
        email = email,
        firstName = firstName,
        id = id,
        isActive = isActive,
        lastName = lastName,
        role = role,
        username = username
    )
}
