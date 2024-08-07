package auth.utils

//import com.auth0.android.jwt.JWT
//import auth.domain.models.JwtPayload
//
//fun decodeJWT(token: String): JwtPayload {
//    val jwt = JWT(token)
//    val jwtPayload = JwtPayload(
//        exp = jwt.expiresAt,
//        iat = jwt.issuedAt,
//        userID = jwt.getClaim("user_id").asInt()
//    )
//    return jwtPayload
//}

import auth.domain.models.JwtPayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import io.ktor.util.*



fun decodeJWT(token: String): JwtPayload {
    val parts = token.split(".")
    if (parts.size != 3) throw IllegalArgumentException("Invalid JWT token")

    val payloadJson = parts[1].decodeBase64String()
    return Json.decodeFromString(payloadJson)
}

fun String.decodeBase64String(): String {
    val decodedBytes = decodeBase64Bytes()
    return decodedBytes.decodeToString()
}