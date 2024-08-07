package auth.domain.models
import kotlinx.serialization.Serializable


//class Users : ArrayList<UserItem>()

@Serializable
data class UserItem(
    var email: String,
    var firstName: String,
    var id: Int,
    var isActive: Boolean,
    var lastName: String,
    var role: String,
    var username: String,
)