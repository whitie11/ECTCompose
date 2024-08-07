package auth.data.remote

import auth.data.dto.UserItemDTO
import common.util.NetworkError
import common.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.header
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.util.network.UnresolvedAddressException
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import net.whiteapps.ectmanager.data.dto.TokenResponseDTO

class AuthApi(
    private val httpClient: HttpClient
) {

    suspend fun getTokens(tr: TokenRequestDTO): Result<TokenResponseDTO, NetworkError> {
        val response = try {
           httpClient.post(
//               urlString = "https://pool.whiteapps.net/user/token/"
               urlString = "http://192.168.1.113:8000/user/token/"
           ){
               contentType(ContentType.Application.Json)
               setBody(tr)
           }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: Exception) {
            var error = e.message
            var errormessage = error
            return Result.Error(NetworkError.UNKNOWN)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val res = response.body<TokenResponseDTO>()
                Result.Success(res)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }

    suspend fun getUser(userId: Int, token: String): Result<UserItemDTO, NetworkError> {
        val response = try {
            httpClient.get(
//                urlString = "https://pool.whiteapps.net/user/getUser/"
                        urlString = "http://192.168.1.113:8000/user/getUser/"
            ){
//                headers {
//                    append("Authorization", "Bearer $token")
//                }
                url {
                    parameters.append("userId", userId.toString())
                }
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
             bearerAuth(token)
            }
        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: Exception) {
            val message = e.message
            val m= message + "!!"
            return Result.Error(NetworkError.UNKNOWN)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val res = response.body<UserItemDTO>()
                Result.Success(res)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }



}